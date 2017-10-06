package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.a307.betcha.Adapters.BetAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBetsActivity extends BetchaActivity {

    RecyclerView recyclerView;
    ArrayList<BetInformation> bets;
    FloatingActionButton createBet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bets = new ArrayList<BetInformation>();
//        fillList();
        createBet = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        createBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Make this create bets activity
                Intent myIntent = new Intent(MyBetsActivity.this, CreateBetActivity.class);
                startActivity(myIntent);
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.recyclerBets);
        String selfToken = SharedPrefsHelper.getSelfToken(this);
        Log.d("Self Token", selfToken);
        ApiHelper.getInstance(this).getUserBets(new LoginRequest(selfToken)).enqueue(new Callback<List<BetInformation>>() {
            @Override
            public void onResponse(Call<List<BetInformation>> call, Response<List<BetInformation>> response) {
                if(response.code() != 200) {
                    Log.d("AUTH ERROR", String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(), "Unable to get bets",Toast.LENGTH_SHORT).show();
                    return;
                }
                bets.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<BetInformation>> call, Throwable t) {

            }
        });
        BetAdapter betAdapter = new BetAdapter(this, bets);
        recyclerView.setAdapter(betAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    protected int getLayoutResource() {
        return R.layout.activity_my_bets;
    }


    /*
        *
        *                           DEBUG TESTING
        *
    */

    public void fillList() {
        bets.add(new BetInformation("Kyle", "10", "Superbowl", "5.00"));
        bets.add(new BetInformation("Kush", "7", "Stanley Cup", "51.00"));
        bets.add(new BetInformation("Peter", "2", "World Series", "30.00"));
        bets.add(new BetInformation("Sidd", "6", "NBA Finals", "40.00"));
        bets.add(new BetInformation("Noah", "4", "UEFA Champions League", "10.00"));
    }
}
