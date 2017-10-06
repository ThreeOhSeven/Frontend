package edu.purdue.a307.betcha.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import edu.purdue.a307.betcha.Adapters.BetAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.BetInformations;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends BetchaActivity {

    RecyclerView recyclerView;
    ArrayList<BetInformation> bets;
    BetAdapter betAdapter;
    String selfToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selfToken = getIntent().getStringExtra("selfToken");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerBets);

    }

    protected int getLayoutResource() { return R.layout.activity_profile; }

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
                betAdapter = new BetAdapter(ProfileActivity.this, bets, selfToken);
                recyclerView.setAdapter(betAdapter);
                recyclerView.invalidate();
                recyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
                betAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<BetInformations> call, Throwable t) {
                Log.d("COMPLETE FAIL", "FAiled");
            }
        });
    }
}
