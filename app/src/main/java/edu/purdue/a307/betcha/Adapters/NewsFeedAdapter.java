package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.purdue.a307.betcha.Activities.BetActivity;
import edu.purdue.a307.betcha.Activities.BetDetailActivity;
import edu.purdue.a307.betcha.Activities.BetchaActivity;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Api.BetchaApi;
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.BetLike;
import edu.purdue.a307.betcha.Models.BetchaResponse;
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
        @BindView(R.id.textDesc)
        public TextView mBetDesc;
        @BindView(R.id.textSpotsLeft)
        public TextView mSpotsLeft;
        @BindView(R.id.textAmount)
        public TextView mAmount;
        @BindView(R.id.likeButton)
        public ImageButton mLikeButton;
        @BindView(R.id.likeCount)
        public TextView mLikeCount;

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
        holder.mBetTitle.setText(dataset.get(position).title);
        holder.mBetDesc.setText(dataset.get(position).text);
        holder.mLikeCount.setText(dataset.get(position).getLikeCount());

        holder.mLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BetLike betLike = new BetLike(1, dataset.get(position).getId());
                ApiHelper.getInstance(activity).postLike(betLike, selfToken).enqueue(new Callback<BetchaResponse>() {
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
