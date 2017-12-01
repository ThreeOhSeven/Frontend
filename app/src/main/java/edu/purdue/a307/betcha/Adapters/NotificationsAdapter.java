package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.purdue.a307.betcha.Activities.ActionBarActivity;
import edu.purdue.a307.betcha.Activities.ConfirmBetActivity;
import edu.purdue.a307.betcha.Activities.FriendsActivity;
import edu.purdue.a307.betcha.Activities.JoinBetActivity;
import edu.purdue.a307.betcha.Activities.MyBetsActivity;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.BetRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.CompleteBetRequest;
import edu.purdue.a307.betcha.Models.Notif;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                        String authToken = SharedPrefsHelper.getSelfToken(activity);
                        ApiHelper.getInstance(activity).getBet(new BetRequest(authToken,
                                info.getBetId())).enqueue(new Callback<Bet>() {
                            @Override
                            public void onResponse(Call<Bet> call, Response<Bet> response) {
                                if (response.code() != 200) {
                                    BToast.makeError(activity, activity.getString(R.string.bet_completion_error));
                                    return;
                                }
                                Intent myIntent = new Intent(activity, ConfirmBetActivity.class);
                                myIntent.putExtra("type", 1);
                                Gson gson = new Gson();
                                myIntent.putExtra("Obj",gson.toJson(response.body()));
                                activity.startActivity(myIntent);
                            }

                            @Override
                            public void onFailure(Call<Bet> call, Throwable t) {
                                BToast.makeServerError(activity);
                            }
                        });


                    }
                });
                break;

            case 1:
                holder.notifCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String authToken = SharedPrefsHelper.getSelfToken(activity);
                        ApiHelper.getInstance(activity).getBet(new BetRequest(authToken,
                                info.getBetId())).enqueue(new Callback<Bet>() {
                            @Override
                            public void onResponse(Call<Bet> call, Response<Bet> response) {
                                if (response.code() != 200) {
                                    BToast.makeError(activity, activity.getString(R.string.bet_completion_error));
                                    return;
                                }
                                Intent myIntent = new Intent(activity, ConfirmBetActivity.class);
                                myIntent.putExtra("type", 1);
                                Gson gson = new Gson();
                                myIntent.putExtra("Obj",gson.toJson(response.body()));
                                activity.startActivity(myIntent);
                            }

                            @Override
                            public void onFailure(Call<Bet> call, Throwable t) {
                                BToast.makeServerError(activity);
                            }
                        });


                    }
                });
                break;

            case 2:
                holder.notifCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String authToken = SharedPrefsHelper.getSelfToken(activity);
                        ApiHelper.getInstance(activity).getBet(new BetRequest(authToken,
                                info.getBetId())).enqueue(new Callback<Bet>() {
                            @Override
                            public void onResponse(Call<Bet> call, Response<Bet> response) {
                                if (response.code() != 200) {
                                    BToast.makeError(activity, activity.getString(R.string.bet_completion_error));
                                    return;
                                }
                                Intent myIntent = new Intent(activity, JoinBetActivity.class);
                                myIntent.putExtra("type", 1);
                                Gson gson = new Gson();
                                myIntent.putExtra("Obj",gson.toJson(response.body()));
                                activity.startActivity(myIntent);
                            }

                            @Override
                            public void onFailure(Call<Bet> call, Throwable t) {
                                BToast.makeServerError(activity);
                            }
                        });


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
