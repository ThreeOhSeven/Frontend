package edu.purdue.a307.betcha.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Models.InviteFriendsObj;
import edu.purdue.a307.betcha.R;

/**
 * Created by kyleohanian on 11/1/17.
 */

public class InviteFriendsAdapter extends RecyclerView.Adapter<InviteFriendsAdapter.MyViewHolder> {

    public List<InviteFriendsObj> items;
    private Context context;
    private int layoutID;

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


    public InviteFriendsAdapter(Context context,List<InviteFriendsObj> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final InviteFriendsObj item = items.get(position);
        holder.buttonMenu.setVisibility(View.INVISIBLE);
        holder.friendName.setText(item.friend.getEmail());
        Picasso.with(context).load(item.friend.getPhotoUrl()).fit().centerInside().into(holder.icon);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isChecked) {
                    item.isChecked = false;
                    holder.cardView.setCardBackgroundColor(Color.WHITE);
                }
                else {
                    item.isChecked = true;
                    holder.cardView.setCardBackgroundColor(Color.GRAY);
                }
//                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
