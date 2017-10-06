package edu.purdue.a307.betcha.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import edu.purdue.a307.betcha.Activities.BetActivity;
import edu.purdue.a307.betcha.Activities.BetDetailActivity;
import edu.purdue.a307.betcha.Activities.BetchaActivity;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Api.BetchaApi;
import edu.purdue.a307.betcha.Models.Bet;
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
    private BetchaActivity activity;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mBetTitle;
        public TextView mBetText;
        public TextView mBetId;
        public ConstraintLayout mItem;
        public ViewHolder(View v) {
            super(v);
            mBetId = (TextView) v.findViewById(R.id.bet_id);
            mBetTitle = (TextView) v.findViewById(R.id.bet_title);
            mBetText = (TextView) v.findViewById(R.id.bet_text);
            mItem = (ConstraintLayout) v.findViewById(R.id.news_feed_item);

        }

    }


    public NewsFeedAdapter(BetchaActivity betchaActivity, List<Bet> bets) {
        super();

        activity = betchaActivity;
        // TODO - Add live data
        /*
        Bet[] testData = new Bet[3];

        testData[0] = new Bet(0, 5, "Test1", "I built this for testing", false);
        testData[1] = new Bet(1, 6, "Test2", "I built this for testing fam", false);
        testData[2] = new Bet(2, 7, "Test3", "I built this for testing bro", false);

        dataset = testData;
        */
        dataset = bets;

    }


    @Override
    public NewsFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_feed_bet, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mBetTitle.setText(dataset.get(position).title);
        holder.mBetText.setText(dataset.get(position).text);
        holder.mBetId.setText(String.format("%d", dataset.get(position).id));

        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(activity, BetDetailActivity.class);
                Gson gson = new Gson();
                myIntent.putExtra("Obj",gson.toJson(dataset.get(position)));
                activity.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
