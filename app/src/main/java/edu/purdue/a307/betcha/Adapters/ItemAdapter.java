package edu.purdue.a307.betcha.Adapters;

/**
 * Created by kyleohanian on 9/20/17.
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import edu.purdue.a307.betcha.R;

/**
 * Created by kyleohanian on 9/13/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

//    private List<ItemObject> items;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView value;
        public TextView label;
        public ImageView imageView, heart;


        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.image);
//            heart = (ImageView)view.findViewById(R.id.heart);
//            value = (TextView) view.findViewById(R.id.value);
//            label = (TextView) view.findViewById(R.id.label);

        }
    }


//    public ItemAdapter(final List<ProfileItem> items) {
//        this.items = new ArrayList<ItemObject>();
//        this.items.addAll(items);
//    }
//
//    public ItemAdapter(final List<NewsFeedItem> items, int i) {
//        this.items = new ArrayList<ItemObject>();
//        this.items.addAll(items);
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.user_attr_card, parent, false);
//        return new MyViewHolder(itemView);
        return null;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        final ItemObject item = items.get(position);
    }

    @Override
    public int getItemCount() {
        return 1;
//        return items.size();
    }
}