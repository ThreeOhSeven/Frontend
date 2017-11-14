package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Models.Comment;
import edu.purdue.a307.betcha.Models.User;
import edu.purdue.a307.betcha.R;

/**
 * Created by kyleohanian on 11/8/17.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Comment> dataset;
    private Activity activity;
    private String selfToken;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        public TextView name;

        @BindView(R.id.comment)
        public TextView comment;

        @BindView(R.id.date)
        public TextView date;

        CircleImageView icon;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
            icon = (CircleImageView)v.findViewById(R.id.iconImage);
        }

    }


    public CommentAdapter(Activity betchaActivity, List<Comment> comments, String selfToken) {
        super();

        this.selfToken = selfToken;
        this.activity = betchaActivity;
        this.dataset = comments;
    }


    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new CommentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, final int position) {
        Comment comment = dataset.get(position);
        holder.name.setText(comment.getUser().getEmail());
        holder.comment.setText(comment.getComment());
        holder.date.setText(comment.getTime());
        IconGenerator.setImage(activity, holder.icon);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}