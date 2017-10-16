package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.BetInformationRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by kushagra on 10/2/17.
 */

public class CreateBetActivity extends BetchaActivity {


    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.amount)
    EditText amount;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.maxUsers)
    EditText maxUsers;
    @BindView(R.id.locked)
    CheckBox locked;
    @BindView(R.id.createBetBtn)
    FloatingActionButton createButton;


    String selfToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View rootView = inflater.inflate(R.layout.fragment_bets, container, false);
        //textView = rootView.findViewById(R.id.nav_bets);
        //textView.setText(getResources().getString(R.string.bets_str));
        //textView = (TextView) findViewById(R.id.nav_bets);
        selfToken = SharedPrefsHelper.getSelfToken(this);

        ButterKnife.bind(this);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO:: Update to server
                BetInformationRequest betInformationRequest = new BetInformationRequest();
                betInformationRequest.amount = amount.getText().toString();
                betInformationRequest.title = title.getText().toString();
                betInformationRequest.description = description.getText().toString();
                betInformationRequest.maxUsers = "10";
                betInformationRequest.locked = "false";
                betInformationRequest.authToken = selfToken;
                ApiHelper.getInstance(getApplicationContext()).
                        createBet(betInformationRequest).enqueue(new Callback<BetchaResponse>() {
                    @Override
                    public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                        if (response.code() != 200) {
                            Log.d("Response Code",String.valueOf(response.code()));
                            Log.d("Response Message",String.valueOf(response.message()));
                            Toast.makeText(getApplicationContext(), "Error in creating bet",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<BetchaResponse> call, Throwable t) {

                    }
                });
            }
        });

    }

    protected int getLayoutResource() { return R.layout.activity_create_bet; }

    @OnClick(R.id.createBetBtn)
    public void create() {
        BetInformationRequest betInformationRequest = new BetInformationRequest();
        betInformationRequest.amount = amount.getText().toString();
        betInformationRequest.title = title.getText().toString();
        betInformationRequest.description = description.getText().toString();
        betInformationRequest.maxUsers = maxUsers.getText().toString();
        betInformationRequest.locked = locked.toString();
        betInformationRequest.authToken = selfToken;
        ApiHelper.getInstance(getApplicationContext()).
                createBet(betInformationRequest).enqueue(new Callback<BetchaResponse>() {
            @Override
            public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                if (response.code() != 200) {
                    Log.d("Response Code",String.valueOf(response.code()));
                    Log.d("Response Message",String.valueOf(response.message()));
                    Toast.makeText(getApplicationContext(), "Error in creating bet",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<BetchaResponse> call, Throwable t) {

            }
        });
    }
}