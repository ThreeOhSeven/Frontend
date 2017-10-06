package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.R;

public class BetActivity extends BetchaActivity {

    BetInformation betInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String gsonInfo = intent.getStringExtra("Object");
        betInformation = new Gson().fromJson(gsonInfo, BetInformation.class);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_bet;
    }
}
