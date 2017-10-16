package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;

import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.EmailResponse;
import edu.purdue.a307.betcha.Models.UserID;
import edu.purdue.a307.betcha.Models.UserIDRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BetActivity extends BetchaActivity {

    BetInformation betInformation;
    TextView title, amount, description, maxUsers, creator;
    Button createButton;
    String selfToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String gsonInfo = intent.getStringExtra("Object");
        betInformation = new Gson().fromJson(gsonInfo, BetInformation.class);
        selfToken = SharedPrefsHelper.getSelfToken(this);

        title = (TextView)findViewById(R.id.txtTitle);
        amount = (TextView)findViewById(R.id.txtAmount);
        description = (TextView) findViewById(R.id.txtDescription);
        maxUsers = (TextView)findViewById(R.id.txtMaxUsers);
        creator = (TextView)findViewById(R.id.txtCreator);

        title.setText(betInformation.getTitle());
        amount.setText(betInformation.getAmount());
        description.setText(betInformation.getDescription());
        maxUsers.setText("10");

        UserIDRequest req = new UserIDRequest();
        req.authToken =selfToken;
        req.id = betInformation.creator;
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
