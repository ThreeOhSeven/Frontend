package edu.purdue.a307.betcha.Adapters;

/**
 * Created by kyleohanian on 9/20/17.
 */
import android.content.Context;
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

//    private List<T> items;
    private Context context;
    private int layoutID;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // TODO:: Components

        public MyViewHolder(View view) {
            super(view);

            // TODO:: Fill in other parts of card view
        }
    }


    public ItemAdapter(Context context, int layoutID/*,List<T> items*/) {
        this.context = context;
        this.layoutID = layoutID;
//        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layoutID, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        final RecyclerItem item = items.get(position);
    }

    @Override
    public int getItemCount() {
        return 1;
//        return items.size();
    }
}