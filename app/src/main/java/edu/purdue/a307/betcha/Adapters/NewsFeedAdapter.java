package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

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
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.AccountInformation;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.BetLikeRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
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

        Bet info = dataset.get(position);
        if(info.isLiked()) {
            holder.mLikeButton.setImageResource(R.drawable.ic_favorite_black_24dp);
            holder.isAlreadyLiked = true;
        }

        User accountInformation = SharedPrefsHelper.getAccountInformation(activity);

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
                            Toast.makeText(activity, "Failed to POST like", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Log.d("Like Response Status", "Successful");
                            if(holder.isAlreadyLiked) {
                                return;
                            }
                            int numLikes = Integer.parseInt(holder.mNumLikes.getText().toString());
                            numLikes++;
                            holder.isAlreadyLiked = true;
                            holder.mNumLikes.setText(String.valueOf(numLikes));
                            holder.mLikeButton.setImageResource(R.drawable.ic_favorite_black_24dp);
//                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<BetchaResponse> call, Throwable t) {
                        Log.d("Like Update: ", "Failure");
                        Toast.makeText(activity, "Failed to POST like", Toast.LENGTH_SHORT).show();
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
                // create a 300px width and 470px height PopupWindow
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                PopupWindow pw = new PopupWindow(layout, width, height, true);
                pw.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                // display the popup in the center
                pw.showAsDropDown(holder.cardView);
            }
        });

        IconGenerator.setImage(activity,holder.icon);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
