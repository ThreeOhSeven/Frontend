package edu.purdue.a307.betcha.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.purdue.a307.betcha.Activities.BetActivity;
import edu.purdue.a307.betcha.Activities.JoinBetActivity;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.IconGenerator;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.BetLikeRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.User;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Peter on 9/28/17.
 *
 * Adapter for the Public News Feed
 */

public class BetUserAdapter extends RecyclerView.Adapter<BetUserAdapter.ViewHolder> {
    private List<User> dataset;
    private Activity activity;
    private String selfToken;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bet_user_text)
        public TextView mEmail;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }

    }


    public BetUserAdapter(Activity betchaActivity, List<User> users, String selfToken) {
        super();

        this.selfToken = selfToken;
        this.activity = betchaActivity;
        this.dataset = users;
    }


    @Override
    public BetUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bet_user, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mEmail.setText(dataset.get(position).getEmail()); // Email
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
