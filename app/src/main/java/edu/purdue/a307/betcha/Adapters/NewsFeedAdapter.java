package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.transition.Transition;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.purdue.a307.betcha.Activities.BetActivity;
import edu.purdue.a307.betcha.Activities.BetDetailActivity;
import edu.purdue.a307.betcha.Activities.JoinBetActivity;
import edu.purdue.a307.betcha.Activities.NewsFeedActivity;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Enums.JoinBetType;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Helpers.Time;
import edu.purdue.a307.betcha.Models.AccountInformation;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.BetCommentAddRequest;
import edu.purdue.a307.betcha.Models.BetComments;
import edu.purdue.a307.betcha.Models.BetCommentsGetRequest;
import edu.purdue.a307.betcha.Models.BetLikeRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.BetComment;
import edu.purdue.a307.betcha.Models.JoinBetRequest;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.Models.User;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Peter on 9/28/17.
 *
 * Adapter for the Public News Feed
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.ViewHolder> {
    private List<Bet> dataset;
    private Activity activity;
    private String selfToken;
    private int type;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv)
        public CardView cardView;
        @BindView(R.id.textTitle)
        public TextView mBetTitle;
        @BindView(R.id.textCreationTime)
        public TextView mCreationTime;
        @BindView(R.id.likeButton)
        public ImageButton mLikeButton;
        @BindView(R.id.numLikes)
        public TextView mNumLikes;
        @BindView(R.id.joinButton)
        public ImageButton mJoinButton;

        @BindView(R.id.commentButton)
        public ImageButton commentButton;


        CircleImageView icon;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
            icon = (CircleImageView)v.findViewById(R.id.iconImage);

        }

    }


    public NewsFeedAdapter(Activity betchaActivity, List<Bet> bets, String selfToken, int type) {
        super();

        this.selfToken = selfToken;
        this.activity = betchaActivity;
        this.dataset = bets;
        this.type = type;
    }


    @Override
    public NewsFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_feed_bet, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Bet info = dataset.get(position);
        holder.mBetTitle.setText(info.getTitle()); // Title
        holder.mNumLikes.setText(String.valueOf(dataset.get(position).getNumLikes())); // Number of Likes
        if(info.getCreationTime() != null) {
            holder.mCreationTime.setText(Time.getTimeDifference(info.getCreationTime())); // Amount
        }
        if(type == 0) {
            holder.mJoinButton.setVisibility(View.INVISIBLE);
            holder.commentButton.setVisibility(View.INVISIBLE);
        }

        Log.d("Show Stuff:", String.valueOf(position) + ": " + String.valueOf(info.isLiked()));
        if(info.isLiked()) {
            holder.mLikeButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        else {
            holder.mLikeButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(activity, BetActivity.class);
                Gson gson = new Gson();
                myIntent.putExtra("Object",gson.toJson(dataset.get(position)));
                myIntent.putExtra("selfToken",selfToken);
                activity.startActivity(myIntent);
            }
        });

        // Add click for liking
        holder.mLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BetLikeRequest betLikeRequest;
                final int liked;
                if(!info.isLiked()) {
                    liked = 1;
                }
                else {
                    liked = 0;
                }
                Log.d("If the thing is liked", String.valueOf(liked));
                betLikeRequest = new BetLikeRequest(liked, dataset.get(position).getId(), selfToken);

                ApiHelper.getInstance(activity).postLike(betLikeRequest).enqueue(new Callback<BetchaResponse>() {
                    @Override
                    public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                        if(response.code() != 200) {
                            Log.d("Like Response Code", Integer.toString(response.code()));
                            Log.d("Like Response Body", response.errorBody().toString());
                            BToast.makeError(activity, activity.getString(R.string.like_bet_error));
                            return;
                        } else {
//                            BToast.makeSuccess(activity, activity.getString(R.string.like_bet_success));
                            if(liked == 0) {
                                int numLikes = Integer.parseInt(holder.mNumLikes.getText().toString());
                                numLikes--;
                                info.setLiked(false);
                                holder.mNumLikes.setText(String.valueOf(numLikes));
                                holder.mLikeButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                                info.setNumLikes(numLikes);
                            }
                            else {
                                int numLikes = Integer.parseInt(holder.mNumLikes.getText().toString());
                                numLikes++;
                                info.setLiked(true);
                                holder.mNumLikes.setText(String.valueOf(numLikes));
                                holder.mLikeButton.setImageResource(R.drawable.ic_favorite_black_24dp);
                                info.setNumLikes(numLikes);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BetchaResponse> call, Throwable t) {
                        Log.d("Like Update: ", "Failure");
                        BToast.makeServerError(activity);
                    }
                });
            }
        });

        holder.mJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent joinIntent = new Intent(activity.getApplicationContext(), JoinBetActivity.class);

                joinIntent.putExtra("type", 0);
                Gson gson = new Gson();
                String json = gson.toJson(dataset.get(position));
                joinIntent.putExtra("Obj", json);

                activity.startActivity(joinIntent);
            }
        });

        holder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Inflate the view from a predefined XML layout
                final View layout = inflater.inflate(R.layout.view_comments,null);


                final RecyclerView recyclerView = layout.findViewById(R.id.comments);

                final Button button = layout.findViewById(R.id.postComment);
                final EditText text = layout.findViewById(R.id.newComment);

                ApiHelper.getInstance(activity).getComments(
                        new BetCommentsGetRequest(selfToken, String.valueOf(info.getId()))).enqueue(new Callback<BetComments>() {
                    @Override
                    public void onResponse(Call<BetComments> call, Response<BetComments> response) {
                        if(response.code() != 200) {
                            return;
                        }

                        List<BetComment> BetComments = response.body().getComments();

                        final CommentAdapter adapter = new CommentAdapter(activity, BetComments, selfToken);

                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(text.getText().toString().isEmpty()) {
                                    return;
                                }

                                String comment = text.getText().toString();

                                ApiHelper.getInstance(activity).addComment(
                                        new BetCommentAddRequest(selfToken,String.valueOf(info.getId()), comment)).enqueue(new Callback<BetchaResponse>() {
                                    @Override
                                    public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                        if(response.code() != 200) {
                                            BToast.makeShort(activity,"Couldn't post comment");
                                            return;
                                        }
                                        ApiHelper.getInstance(activity).getComments(
                                                new BetCommentsGetRequest(selfToken,
                                                        String.valueOf(info.getId()))).enqueue(new Callback<edu.purdue.a307.betcha.Models.BetComments>() {
                                            @Override
                                            public void onResponse(Call<BetComments> call, Response<BetComments> response) {
                                                if(response.code() != 200) {
                                                    return;
                                                }
                                                adapter.addAll(response.body().getComments());
                                            }

                                            @Override
                                            public void onFailure(Call<BetComments> call, Throwable t) {

                                            }
                                        });

//                                        BToast.makeShort(activity,"Success");
                                    }

                                    @Override
                                    public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                        BToast.makeServerError(activity);
                                    }
                                });
                            }
                        });

                        int width = LinearLayout.LayoutParams.MATCH_PARENT;
                        int height = 1000;
                        final PopupWindow pw = new PopupWindow(layout, width, height, true);
                        pw.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                        pw.showAtLocation(holder.cardView,Gravity.CENTER,0,0);



                    }

                    @Override
                    public void onFailure(Call<BetComments> call, Throwable t) {

                    }
                });
            }
        });

        if(info.getColor() == null || info.getIcon() == null) {
            return;
        }
        IconGenerator.setImageWithPredefinedNums(activity,holder.icon,
                Integer.parseInt(info.getColor()), Integer.parseInt(info.getIcon()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
