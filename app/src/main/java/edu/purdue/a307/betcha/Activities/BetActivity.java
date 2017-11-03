package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.purdue.a307.betcha.Adapters.BetUserAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.EmailResponse;
import edu.purdue.a307.betcha.Models.UserIDRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BetActivity extends BetchaActivity {

    Bet betInformation;
    TextView title, amount, description, maxUsers, creator;
    Button createButton;
    String selfToken;
    RecyclerView betters;

    @BindView(R.id.txtSideA)
    TextView sideA;
    @BindView(R.id.txtSideB)
    TextView sideB;
    @BindView(R.id.txtStatus)
    TextView status;
    @BindView(R.id.txtWinner)
    TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String gsonInfo = intent.getStringExtra("Object");
        betInformation = new Gson().fromJson(gsonInfo, Bet.class);
        selfToken = SharedPrefsHelper.getSelfToken(this);

        title = (TextView)findViewById(R.id.txtTitle);
        amount = (TextView)findViewById(R.id.txtAmount);
        description = (TextView) findViewById(R.id.txtDescription);
        maxUsers = (TextView)findViewById(R.id.txtMaxUsers);
        creator = (TextView)findViewById(R.id.txtCreator);
        betters = (RecyclerView)findViewById(R.id.bettersList);

        betters.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        betters.setLayoutManager(layoutManager);

        if(betInformation.users.size() > 0) {
            betters.setAdapter(new BetUserAdapter(this, betInformation.users, selfToken));
        }

        try{
            title.setText(betInformation.getTitle());
        } catch (Exception e) {
            // Do nothing
        }
        try{
            amount.setText(String.valueOf(betInformation.getAmount()));
        } catch (Exception e) {
            // Do nothing
        }
        try{
            description.setText(betInformation.getDescription());
        } catch (Exception e) {
            // Do nothing
        }
        try{
            maxUsers.setText(betInformation.getMaxUsers());
        } catch (Exception e) {
            // Do nothing
        }
        try {
            sideA.setText(betInformation.getSideA());
            sideB.setText(betInformation.getSideB());
            if(betInformation.isLocked()) {
                status.setText("Locked");
            }
            else {
                status.setText("In Progress");
            }
            if(betInformation.isComplete()) {
                status.setText("Complete");
            }
            winner.setText(String.valueOf(betInformation.isWinner()));

        } catch (Exception e) {
            // Do nothing
        }





        UserIDRequest req = new UserIDRequest();
        req.authToken =selfToken;

        req.id = String.valueOf(betInformation.getCreatorId());
        ApiHelper.getInstance(this).getUserByID(req).enqueue(new Callback<EmailResponse>() {
            @Override
            public void onResponse(Call<EmailResponse> call, Response<EmailResponse> response) {
                if (response.code() != 200) {
                    Log.d("Response Code",String.valueOf(response.code()));
                    Log.d("Response Message",String.valueOf(response.message()));
                    Toast.makeText(getApplicationContext(), "Couldn't get information",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                creator.setText(response.body().getEmail());
            }

            @Override
            public void onFailure(Call<EmailResponse> call, Throwable t) {

            }
        });


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_bet;
    }
}
