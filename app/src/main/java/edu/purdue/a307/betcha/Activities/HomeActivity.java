package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.os.Bundle;

import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Helpers.MyFirebaseInstanceIdService;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.PhotoUrlRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BetchaActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String token = intent.getStringExtra("authToken");

        MyFirebaseInstanceIdService myFirebaseInstanceIdService = new MyFirebaseInstanceIdService();
        myFirebaseInstanceIdService.authToken = token;

        myFirebaseInstanceIdService.onTokenRefresh();

        super.onCreate(savedInstanceState);
        BToast.makeSuccess(this, "Logged in as: " + SharedPrefsHelper.getAccountInformation(
                getApplicationContext()).getEmail());

        ApiHelper.getInstance(HomeActivity.this).updatePhotoUrl(
                        new PhotoUrlRequest(token, SharedPrefsHelper.getPhotoURL(
                                HomeActivity.this))).enqueue(new Callback<BetchaResponse>() {
            @Override
            public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {

            }

            @Override
            public void onFailure(Call<BetchaResponse> call, Throwable t) {

            }
        });

    }

    protected int getLayoutResource() { return R.layout.activity_home; }
}
