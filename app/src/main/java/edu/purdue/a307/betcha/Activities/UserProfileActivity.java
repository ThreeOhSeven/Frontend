package edu.purdue.a307.betcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
import edu.purdue.a307.betcha.Models.TransactionBalance;
import edu.purdue.a307.betcha.Models.UserProfileRequest;
import edu.purdue.a307.betcha.Models.UserProfileResponse;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends BetchaActivity {

    RecyclerView recyclerView;
    List<Bet> bets;
    BetAdapter betAdapter;
    String selfToken;
    TextView balance;
    CircleImageView imgView;

    @BindView(R.id.user_name)
    TextView name;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        selfToken = SharedPrefsHelper.getSelfToken(this);
        recyclerView = (RecyclerView)findViewById(R.id.userRecyclerBets);
        balance = (TextView) findViewById(R.id.user_balance);
        imgView = (CircleImageView)findViewById(R.id.user_profile_image);
        //name.setText(SharedPrefsHelper.getAccountInformation(getApplicationContext()).getEmail()); // Get user email
        imgView.setImageResource(R.mipmap.ic_launcher_round);
        id = getIntent().getIntExtra("id", 21);
        Log.d("User Id", String.valueOf(id));

    }

    protected int getLayoutResource() { return R.layout.activity_user_profile; }

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
        ApiHelper.getInstance(this).getUserProfileBets(new UserProfileRequest(selfToken, id)).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if(response.code() != 200) {
                    Log.d("AUTH ERROR", String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(), "Unable to get bets",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("Bets size", String.valueOf(response.body().getBets().size()));
                bets = response.body().getBets();
                betAdapter = new BetAdapter(UserProfileActivity.this, bets, selfToken, BetAdapterType.USERPROFILE);
                recyclerView.setAdapter(betAdapter);
                recyclerView.invalidate();
                recyclerView.setLayoutManager(new LinearLayoutManager(UserProfileActivity.this));
                betAdapter.notifyDataSetChanged();
                name.setText(response.body().getUser().getEmail());
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                Log.d("COMPLETE FAIL", "Failed");
            }
        });
    }
}
