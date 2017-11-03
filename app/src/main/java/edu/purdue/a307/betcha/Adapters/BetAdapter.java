package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.purdue.a307.betcha.Activities.BetActivity;
import edu.purdue.a307.betcha.Activities.BetchaActivity;
import edu.purdue.a307.betcha.Activities.EditBetActivity;
import edu.purdue.a307.betcha.Activities.JoinBetActivity;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Enums.AdapterType;
import edu.purdue.a307.betcha.Enums.BetAdapterType;
import edu.purdue.a307.betcha.Fragments.BetInvitesFragment;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.CompleteBetRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kyleohanian on 10/3/17.
 */

public class BetAdapter extends RecyclerView.Adapter<BetAdapter.MyViewHolder> {

    private List<Bet> items;
    private Activity activity;
    private String selfToken;
    public BetAdapterType type;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv)
        CardView cardView;
        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.textAmount)
        TextView textAmount;
        @BindView(R.id.textSpotsLeft)
        TextView textSpotsLeft;
        @BindView(R.id.buttonMenu)
        ImageButton buttonMenu;
        CircleImageView icon;
        @BindView(R.id.likeButton)
        ImageButton likeButton;


        boolean isLiked;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            icon = view.findViewById(R.id.iconImage);
            isLiked = false;

        }
    }


    public BetAdapter(Activity activity, List<Bet> items, String selfToken, BetAdapterType type) {
        this.activity = activity;
        this.items = items;
        this.selfToken = selfToken;
        this.type = type;
    }

    @Override
    public BetAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bet_information, parent, false);
        return new BetAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BetAdapter.MyViewHolder holder, final int position) {
        final Bet info = items.get(position);
        holder.textTitle.setText(info.getTitle());
        holder.textAmount.setText("$"+info.getAmount());
        // TODO: needs to be actual spots left
//        holder.textSpotsLeft.setText("Spots Left: " + info.maxUsers);
        holder.buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(activity, holder.buttonMenu);
                popup.inflate(R.menu.item_bet_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_item_edit:
                                Intent myIntent = new Intent(activity, EditBetActivity.class);
                                String json = new Gson().toJson(info);
                                myIntent.putExtra("jsonObj", json);
                                activity.startActivity(myIntent);
                            case R.id.menu_item_complete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                builder.setMessage("Which side won?");
                                builder.setTitle("Completing Bet");
                                builder.setCancelable(false);
                                builder.setPositiveButton(info.getSideB(), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialogInterface, int i) {
                                        String authToken = SharedPrefsHelper.getSelfToken(activity);
                                        ApiHelper.getInstance(activity).completeBet(new CompleteBetRequest(authToken,
                                                String.valueOf(info.getId()),"1")).enqueue(new Callback<BetchaResponse>() {
                                            @Override
                                            public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                                if(response.code() != 200) {
                                                    BToast.makeShort(activity, "This completion failed (ERROR)");
                                                }
                                                dialogInterface.dismiss();
                                            }

                                            @Override
                                            public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                                BToast.makeShort(activity,"This completion failed (FAILED");
                                                dialogInterface.dismiss();
                                            }
                                        });
                                    }
                                });
                                builder.setNegativeButton(info.getSideA(), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialogInterface, int i) {
                                        String authToken = SharedPrefsHelper.getSelfToken(activity);
                                        ApiHelper.getInstance(activity).completeBet(new CompleteBetRequest(authToken,
                                                String.valueOf(info.getId()),"0")).enqueue(new Callback<BetchaResponse>() {
                                            @Override
                                            public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                                if(response.code() != 200) {
                                                    BToast.makeShort(activity, "This completion failed (ERROR)");
                                                }
                                                dialogInterface.dismiss();
                                            }

                                            @Override
                                            public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                                BToast.makeShort(activity,"This completion failed (FAILED");
                                                dialogInterface.dismiss();
                                            }
                                        });
                                    }
                                });
                                builder.show();
                        }
                        return false;
                    }
                });

                popup.show();
            }
        });
        IconGenerator.setImage(activity,holder.icon);
        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.isLiked) {
                    holder.likeButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    holder.isLiked = false;
                }
                else {
                    holder.likeButton.setImageResource(R.drawable.ic_favorite_black_24dp);
                    holder.isLiked = true;
                }
            }
        });

        if(type == BetAdapterType.PENDING) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(activity, JoinBetActivity.class);
                    Gson gson = new Gson();
                    myIntent.putExtra("Obj",gson.toJson(info));
                    activity.startActivity(myIntent);
                }
            });
        } else {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(activity, BetActivity.class);
                    Gson gson = new Gson();
                    myIntent.putExtra("Object",gson.toJson(info));
                    myIntent.putExtra("selfToken",selfToken);
                    activity.startActivity(myIntent);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
