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
import edu.purdue.a307.betcha.Models.BetInformations;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBetsActivity extends BetchaActivity {

    RecyclerView recyclerView;
    ArrayList<BetInformation> bets;
    BetAdapter betAdapter;
    FloatingActionButton createBet;
    String selfToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bets = new ArrayList<BetInformation>();
        selfToken = getIntent().getStringExtra("selfToken");
//        fillList();
        createBet = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        createBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Make this create bets activity
                Intent myIntent = new Intent(MyBetsActivity.this, CreateBetActivity.class);
                myIntent.putExtra("selfToken", selfToken);
                startActivity(myIntent);
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.recyclerBets);
        Log.d("Self Token", selfToken);
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

    @Override
    public void onResume() {
        super.onResume();
        ApiHelper.getInstance(this).getUserBets(new LoginRequest(selfToken)).enqueue(new Callback<BetInformations>() {
            @Override
            public void onResponse(Call<BetInformations> call, Response<BetInformations> response) {
                if(response.code() != 200) {
                    Log.d("AUTH ERROR", String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(), "Unable to get bets",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("Bets size", String.valueOf(response.body().getMyBets().size()));
                bets = response.body().getMyBets();
                betAdapter = new BetAdapter(MyBetsActivity.this, bets,selfToken);
                recyclerView.setAdapter(betAdapter);
                recyclerView.invalidate();
                recyclerView.setLayoutManager(new LinearLayoutManager(MyBetsActivity.this));
                betAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<BetInformations> call, Throwable t) {
                Log.d("COMPLETE FAIL", "FAiled");
            }
        });
    }
}
