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
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Enums.BetAdapterType;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.Bets;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.Models.RecordResponse;
import edu.purdue.a307.betcha.Models.TransactionBalance;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsActivity extends BetchaActivity {

    RecyclerView recyclerView;
    List<Bet> bets;
    BetAdapter betAdapter;
    String selfToken;
    TextView balance;
    CircleImageView imgView;
    TextView recordView;

    @BindView(R.id.name)
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        selfToken = SharedPrefsHelper.getSelfToken(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerBets);
        balance = (TextView) findViewById(R.id.balance);
        imgView = (CircleImageView)findViewById(R.id.profile_image);
        recordView = (TextView)findViewById(R.id.record);
        name.setText(SharedPrefsHelper.getAccountInformation(getApplicationContext()).getEmail());
        String str = SharedPrefsHelper.getPhotoURL(getApplicationContext());
        Picasso.with(this).load(str).fit().centerInside().into(imgView);

    }

    protected int getLayoutResource() { return R.layout.activity_profile; }

    @Override
    public void onResume() {
        super.onResume();
        ApiHelper.getInstance(this).getBalance(new LoginRequest(selfToken)).enqueue(new Callback<TransactionBalance>() {
            @Override
            public void onResponse(Call<TransactionBalance> call, Response<TransactionBalance> response) {
                if(response.code() != 200) {
                    BToast.makeShort(getApplicationContext(), "Unable to get balance");
                    return;
                }

                balance.setText("Balance: " + String.valueOf(response.body().getCurrent_balance()));
            }

            @Override
            public void onFailure(Call<TransactionBalance> call, Throwable t) {

            }
        });

    }
}