package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.purdue.a307.betcha.Activities.ConfirmBetActivity;
import edu.purdue.a307.betcha.Activities.FriendsActivity;
import edu.purdue.a307.betcha.Activities.JoinBetActivity;
import edu.purdue.a307.betcha.Activities.MyBetsActivity;
import edu.purdue.a307.betcha.Models.Notif;
import edu.purdue.a307.betcha.R;

/**
 * Created by kyleohanian on 10/3/17.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<edu.purdue.a307.betcha.Adapters.NotificationsAdapter.MyViewHolder> {

    private List<Notif> items;
    private Activity activity;
    private String selfToken;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.notifCardView)
        CardView notifCardView;
        @BindView(R.id.notifTitle)
        TextView notifTitle;
        @BindView(R.id.notifMessage)
        TextView notifMessage;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public NotificationsAdapter(Activity activity, List<Notif> items, String selfToken) {
        this.activity = activity;
        this.items = items;
        this.selfToken = selfToken;
    }

    @Override
    public NotificationsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notifications, parent, false);
        return new edu.purdue.a307.betcha.Adapters.NotificationsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final edu.purdue.a307.betcha.Adapters.NotificationsAdapter.MyViewHolder holder, final int position) {
        final Notif info = items.get(position);
        holder.notifTitle.setText(info.getTitle());
        holder.notifMessage.setText(info.getMessage());

        if(info.isViewed()) {
            holder.notifCardView.setCardBackgroundColor(Color.LTGRAY);
        }

        switch (info.getType()) {
            case 0:
                holder.notifCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(activity, MyBetsActivity.class);
                        activity.startActivity(myIntent);
                    }
                });
                break;

            case 1:
                holder.notifCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(activity, MyBetsActivity.class);
                        activity.startActivity(myIntent);
                    }
                });
                break;

            case 2:
                holder.notifCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(activity, MyBetsActivity.class);
                        activity.startActivity(myIntent);
                    }
                });
                break;

            case 3:
                holder.notifCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(activity, FriendsActivity.class);
                        activity.startActivity(myIntent);
                    }
                });
                break;
        }





    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}