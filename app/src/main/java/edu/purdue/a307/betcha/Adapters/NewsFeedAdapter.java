package edu.purdue.a307.betcha.Adapters;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.purdue.a307.betcha.Models.PublicFeedItem;
import edu.purdue.a307.betcha.R;

/**
 * Created by Peter on 9/28/17.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.ViewHolder> {
    private String[] dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public NewsFeedAdapter() {
        super();
        String[] testData = {
                "bet 1",
                "bet 2",
                "bet 3"
        };

        dataset = testData;
    }


    @Override
    public NewsFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bet_information, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(dataset[position]);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }
}
