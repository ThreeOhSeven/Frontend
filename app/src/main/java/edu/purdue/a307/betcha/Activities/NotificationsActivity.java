package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.purdue.a307.betcha.Adapters.BetAdapter;
import edu.purdue.a307.betcha.Adapters.NotificationsAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Enums.BetAdapterType;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.Bets;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.Models.Notif;
import edu.purdue.a307.betcha.Models.NotificationsResponse;
import edu.purdue.a307.betcha.Models.RecordResponse;
import edu.purdue.a307.betcha.Models.TransactionBalance;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsActivity extends BetchaActivity {

    RecyclerView recyclerView;
    List<Notif> notifications;
    NotificationsAdapter adapter;
    String selfToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        selfToken = SharedPrefsHelper.getSelfToken(this);
        recyclerView = (RecyclerView)findViewById(R.id.notifRecycler);


    }

    protected int getLayoutResource() { return R.layout.activity_notifications; }

    @Override
    public void onResume() {
        super.onResume();
        ApiHelper.getInstance(this).getNotifications(new LoginRequest(selfToken)).enqueue(new Callback<NotificationsResponse>() {
            @Override
            public void onResponse(Call<NotificationsResponse> call, Response<NotificationsResponse> response) {
                if(response.code() != 200) {
                    Log.d("Server Response", String.valueOf(response.code()));
                    BToast.makeShort(getApplicationContext(), "Unable to get notifications");
                    return;
                }

                notifications = response.body().getNotifications();
                adapter = new NotificationsAdapter(NotificationsActivity.this, notifications, selfToken);
                recyclerView.setAdapter(adapter);
                recyclerView.invalidate();
                recyclerView.setLayoutManager(new LinearLayoutManager(NotificationsActivity.this));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NotificationsResponse> call, Throwable t) {
                Log.d("Post Fail", "WE SUCK AT API STUFF");
            }
        });

    }
}