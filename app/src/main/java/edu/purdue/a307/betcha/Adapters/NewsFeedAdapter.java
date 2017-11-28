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
import edu.purdue.a307.betcha.Models.AccountInformation;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.BetLikeRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.Comment;
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
        @BindView(R.id.textAmount)
        public TextView mAmount;
        @BindView(R.id.likeButton)
        public ImageButton mLikeButton;
        @BindView(R.id.numLikes)
        public TextView mNumLikes;
        @BindView(R.id.joinButton)
        public ImageButton mJoinButton;

        @BindView(R.id.commentButton)
        public ImageButton commentButton;

        public boolean isAlreadyLiked;

        CircleImageView icon;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
            icon = (CircleImageView)v.findViewById(R.id.iconImage);
            isAlreadyLiked = false;

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
        holder.mBetTitle.setText(dataset.get(position).getTitle()); // Title
        holder.mNumLikes.setText(String.valueOf(dataset.get(position).getNumLikes())); // Number of Likes
        holder.mAmount.setText("$"+ String.valueOf(dataset.get(position).getAmount())); // Amount

        if(type == 0) {
            holder.mJoinButton.setVisibility(View.INVISIBLE);
        }

        final Bet info = dataset.get(position);
        Log.d("Show Stuff:", String.valueOf(position) + ": " + String.valueOf(info.isLiked()));
        if(info.isLiked()) {
            holder.mLikeButton.setImageResource(R.drawable.ic_favorite_black_24dp);
            holder.isAlreadyLiked = true;
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
                BetLikeRequest betLikeRequest = new BetLikeRequest(1, dataset.get(position).getId(), selfToken);

                ApiHelper.getInstance(activity).postLike(betLikeRequest).enqueue(new Callback<BetchaResponse>() {
                    @Override
                    public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                        if(response.code() != 200) {
                            Log.d("Like Response Code", Integer.toString(response.code()));
                            Log.d("Like Response Body", response.errorBody().toString());
                            BToast.makeError(activity, activity.getString(R.string.like_bet_error));
                            return;
                        } else {
                            BToast.makeSuccess(activity, activity.getString(R.string.like_bet_success));
                            if(holder.isAlreadyLiked) {
                                return;
                            }
                            int numLikes = Integer.parseInt(holder.mNumLikes.getText().toString());
                            numLikes++;
                            holder.isAlreadyLiked = true;
                            info.setLiked(true);
                            holder.mNumLikes.setText(String.valueOf(numLikes));
                            holder.mLikeButton.setImageResource(R.drawable.ic_favorite_black_24dp);
//                            notifyDataSetChanged();
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
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Inflate the view from a predefined XML layout
                View layout = inflater.inflate(R.layout.view_comments,null);


                RecyclerView recyclerView = layout.findViewById(R.id.comments);
                ArrayList<Comment> comments = new ArrayList<Comment>();
                comments.add(new Comment(new User("","","Kyle Ohanian",""),"This is such a cool app!", "1 hr"));
                comments.add(new Comment(new User("","","Peter Jones",""),"Whoever invented this app is a super genius", "58 min"));
                comments.add(new Comment(new User("","","Siddharth Shah",""),"Ehhh......it's not that good", "50 min"));
                comments.add(new Comment(new User("","","Kyle Ohanian",""),"@Siddharth Shah, what are you talking about????? This is by far " +
                        "the greatest creation for the betting industry", "41 min"));
                comments.add(new Comment(new User("","","Kushagra Kushagra",""),"lol", "33 min"));
                comments.add(new Comment(new User("","","Noah Smith",""),"I'm with Kyle", "10 min"));
                comments.add(new Comment(new User("","","Peter Jones",""),"^ Same", "Just now"));

                recyclerView.setAdapter(new CommentAdapter(activity, comments, ""));
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));



                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = 1000;
                final PopupWindow pw = new PopupWindow(layout, width, height, true);
                pw.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                pw.showAtLocation(holder.cardView,Gravity.CENTER,0,0);
            }
        });

        IconGenerator.setImage(activity,holder.icon);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
