package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import edu.purdue.a307.betcha.Activities.ProfileActivity;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Enums.AdapterType;
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.FriendItem;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kyleohanian on 10/3/17.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {

    public List<FriendItem> items;
    private Activity activity;
    private String selfToken;

    private AdapterType adapterType;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv)
        CardView cardView;
        @BindView(R.id.friendName)
        TextView friendName;
        @BindView(R.id.buttonMenu)
        ImageButton buttonMenu;
        CircleImageView icon;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            icon = view.findViewById(R.id.iconImage);

        }
    }


    public FriendAdapter(Activity activity, List<FriendItem> items, String selfToken, AdapterType adapterType) {
        this.activity = activity;
        this.items = items;
        this.selfToken = selfToken;
        this.adapterType = adapterType;
    }

    @Override
    public FriendAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false);
        return new FriendAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FriendAdapter.MyViewHolder holder, final int position) {
        final FriendItem info = items.get(position);

        if(info.getStatus().equals("0")) {
            holder.cardView.setBackgroundResource(R.color.colorAccent);
            holder.buttonMenu.setBackgroundColor(ContextCompat.getColor(activity,R.color.colorAccent));
        }
        holder.friendName.setText(info.getFriend().getEmail());
        // TODO: needs to be actual spots left
        holder.buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(activity, holder.buttonMenu);
                if (adapterType == AdapterType.FRIENDS_BETS) {
                    popup.inflate(R.menu.remove);
                }
                else {
                    if (info.getStatus().equals("0")) {
                        popup.inflate(R.menu.add_and_delete);
                    } else
                        popup.inflate(R.menu.delete);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(final MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_item_delete:
                                    Log.d("ITEM ID", String.valueOf(item.getItemId()));
                                    ApiHelper.getInstance(activity).deleteFriend(info.getFriend().getId(),
                                            new LoginRequest(selfToken)).enqueue(new Callback<BetchaResponse>() {
                                        @Override
                                        public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                            if (response.code() != 200) {
                                                Log.d("Response Code", String.valueOf(response.code()));
                                                Log.d("Response Message", String.valueOf(response.message()));
                                                Toast.makeText(activity, "Can't remove bets", Toast.LENGTH_SHORT).show();
                                                return;
                                            } else {
                                                Log.d("IN OK", "IT IS OK");
                                                items.remove(position);
                                                notifyDataSetChanged();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                            Log.d("DELETE", "FAILED");
                                        }
                                    });
                                    break;
                                case R.id.menu_item_add:
                                    ApiHelper.getInstance(activity).addFriend(
                                            info.getFriend().getId(), new LoginRequest(selfToken)).enqueue(new Callback<BetchaResponse>() {
                                        @Override
                                        public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                            if (response.code() != 200) {
                                                Log.d("AUTH ERROR", String.valueOf(response.code()));
                                                Toast.makeText(activity, "Unable to send friend request", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            holder.cardView.setBackgroundResource(R.color.colorIcon);
                                            holder.buttonMenu.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorIcon));
                                            holder.buttonMenu.setVisibility(View.INVISIBLE);
                                        }

                                        @Override
                                        public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                            Toast.makeText(activity, "Unable to send friend request", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            }
                            return true;
                        }
                    });

                    popup.show();
                }
            }
        });
        IconGenerator.setImage(activity,holder.icon);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent myIntent = new Intent(activity, ProfileActivity.class);
//                Gson gson = new Gson();
//                myIntent.putExtra("Object",gson.toJson(info));
//                activity.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
