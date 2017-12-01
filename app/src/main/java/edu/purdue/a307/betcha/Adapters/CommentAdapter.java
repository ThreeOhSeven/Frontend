package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Helpers.Time;
import edu.purdue.a307.betcha.Models.BetComment;
import edu.purdue.a307.betcha.Models.BetCommentDeleteRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.User;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kyleohanian on 11/8/17.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<BetComment> dataset;
    private Activity activity;
    private String selfToken;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv)
        public CardView cv;

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


    public CommentAdapter(Activity betchaActivity, List<BetComment> comments, String selfToken) {
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
        final BetComment comment = dataset.get(position);
        String email = comment.getEmail();
        int index = email.indexOf('@');
        String subEmail = email.substring(0, index);
        holder.name.setText(subEmail);
        holder.comment.setText(comment.getText());
        Log.d("Date", comment.getCreationTime());
        holder.date.setText(Time.getTimeDifference(comment.getCreationTime()));
        Picasso.with(activity).load(comment.getPhotoUrl()).fit().centerInside().into(holder.icon);
        holder.cv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Confirm Delete");
                builder.setMessage("Are you sure you would like to delete this comment");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ApiHelper.getInstance(activity).deleteComment(
                                new BetCommentDeleteRequest(selfToken, comment.getId())
                        ).enqueue(new Callback<BetchaResponse>() {
                            @Override
                            public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                if(response.code() != 200) {
                                    BToast.makeError(activity, activity.getString(R.string.comment_bet_remove_error));
                                    try {
                                        Log.d("ERROR MESSAGE", response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return;
                                }
                                dataset.remove(position);
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                BToast.makeServerError(activity);
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addAll(List<BetComment> comments) {
        this.dataset = comments;
        notifyDataSetChanged();
    }
}
