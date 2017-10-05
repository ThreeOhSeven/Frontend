package edu.purdue.a307.betcha.Adapters;

import android.content.Context;
import android.media.Image;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.purdue.a307.betcha.Activities.BetchaActivity;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.R;

/**
 * Created by kyleohanian on 10/3/17.
 */

public class BetAdapter extends RecyclerView.Adapter<BetAdapter.MyViewHolder> {

    private List<BetInformation> items;
    private BetchaActivity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // TODO:: Components
        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.textAmount)
        TextView textAmount;
        @BindView(R.id.textSpotsLeft)
        TextView textSpotsLeft;
        @BindView(R.id.buttonMenu)
        ImageButton buttonMenu;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


    public BetAdapter(BetchaActivity activity,List<BetInformation> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public BetAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bet_information, parent, false);
        return new BetAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BetAdapter.MyViewHolder holder, final int position) {
        final BetInformation info = items.get(position);
        holder.textTitle.setText(info.title);
        holder.textAmount.setText(info.amount);
        // TODO: needs to be actual spots left
        holder.textSpotsLeft.setText(info.maxUsers);
        holder.buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(activity, holder.buttonMenu);
                //Inflating the Popup using xml file
                popup.inflate(R.menu.item_bet_menu);
                //registering popup with OnMenuItemClickListener
                popup.getMenu().getItem(0).setIcon(R.drawable.ic_edit_24dp);
                popup.getMenu().getItem(1).setIcon(R.drawable.ic_delete_24dp);
                popup.getMenu().getItem(2).setIcon(R.drawable.ic_lock_close_24dp);
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

                popup.show(); //showing popup menu
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
