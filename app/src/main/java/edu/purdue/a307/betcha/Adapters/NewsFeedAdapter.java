package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.BetLikeRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.LoginRequest;
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

        CircleImageView icon;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
            icon = (CircleImageView)v.findViewById(R.id.iconImage);

        }

    }


    public NewsFeedAdapter(Activity betchaActivity, List<Bet> bets, String selfToken) {
        super();

        this.selfToken = selfToken;
        this.activity = betchaActivity;
        this.dataset = bets;
    }


    @Override
    public NewsFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_feed_bet, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mBetTitle.setText(dataset.get(position).getTitle()); // Title
        holder.mNumLikes.setText(String.valueOf(dataset.get(position).getNumLikes())); // Number of Likes
        holder.mAmount.setText("$"+ String.valueOf(dataset.get(position).getAmount())); // Amount

        // Add filled heart if it has been liked by the user
        if(dataset.get(position).isLiked()) {
            holder.mLikeButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        }

        // Add click for liking
        holder.mLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BetLikeRequest betLikeRequest = new BetLikeRequest(1, dataset.get(position).getId(), selfToken);

                if(dataset.get(position).isLiked()) {
                    betLikeRequest.setLike(0);
                }

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
                            notifyDataSetChanged();
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
                BetLikeRequest betLikeRequest = new BetLikeRequest(1, dataset.get(position).getId(), selfToken);

                if(dataset.get(position).isLiked()) {
                    betLikeRequest.setLike(0);
                }

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
                            notifyDataSetChanged();
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

        IconGenerator.setImage(activity,holder.icon);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
