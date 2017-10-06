package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.R;

public class BetActivity extends BetchaActivity {

    BetInformation betInformation;
    EditText title, amount, description;
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String gsonInfo = intent.getStringExtra("Object");
        betInformation = new Gson().fromJson(gsonInfo, BetInformation.class);

        title = (EditText)findViewById(R.id.title);
        amount = (EditText)findViewById(R.id.amount_to_bet);
        description = (EditText)findViewById(R.id.bet_description);

        title.setText(betInformation.getTitle());
        amount.setText(betInformation.getAmount());
        description.setText(betInformation.getDescription());


        createButton = (Button)findViewById(R.id.createBetBtn);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO:: Update to server
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_bet;
    }
}
