package edu.purdue.a307.betcha.Adapters;

import android.content.Context;
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

import java.util.List;

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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mBetTitle;
        public TextView mBetText;
        public TextView mBetId;
        public ViewHolder(View v) {
            super(v);
            mBetId = (TextView) v.findViewById(R.id.bet_id);
            mBetTitle = (TextView) v.findViewById(R.id.bet_title);
            mBetText = (TextView) v.findViewById(R.id.bet_text);
        }
    }

    public NewsFeedAdapter(List<Bet> bets) {
        super();
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mBetTitle.setText(dataset.get(position).title);
        holder.mBetText.setText(dataset.get(position).text);
        holder.mBetId.setText(String.format("%d", dataset.get(position).id));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
