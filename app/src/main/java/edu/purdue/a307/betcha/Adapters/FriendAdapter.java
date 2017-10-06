package edu.purdue.a307.betcha.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.FriendItem;
import edu.purdue.a307.betcha.R;

/**
 * Created by kyleohanian on 10/3/17.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {

    private List<FriendItem> items;
    private BetchaActivity activity;

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


    public FriendAdapter(BetchaActivity activity,List<FriendItem> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public FriendAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bet_information, parent, false);
        return new FriendAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FriendAdapter.MyViewHolder holder, final int position) {
        final FriendItem info = items.get(position);
        holder.friendName.setText(info.getFriend().getUsername());
        // TODO: needs to be actual spots left
        holder.buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(activity, holder.buttonMenu);
                popup.inflate(R.menu.item_bet_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                activity,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return false;
                    }
                });

                popup.show();
            }
        });
        IconGenerator.setImage(activity,holder.icon);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(activity, BetActivity.class);
                Gson gson = new Gson();
                myIntent.putExtra("Object",gson.toJson(info));
                activity.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
