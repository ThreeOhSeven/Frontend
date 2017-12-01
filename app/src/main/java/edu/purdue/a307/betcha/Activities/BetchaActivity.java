package edu.purdue.a307.betcha.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.BDialog;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Listeners.AlertDialogListener;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BetchaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    GoogleApiClient apiClient;
    ActionBarDrawerToggle toggle;
    String selfToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betcha);
        selfToken = SharedPrefsHelper.getSelfToken(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        apiClient = new GoogleApiClient.Builder(this).addApi(
                Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

        inflateCurrentLayout();
    }

    protected abstract int getLayoutResource();

    protected void inflateCurrentLayout() {
        int res = getLayoutResource();
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View replacer = inflater.inflate(res, null);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.replaceView);
        relativeLayout.addView(replacer);
    }

    @Override
    public void onStart() {
        super.onStart();
        apiClient.connect();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.nav_sign_out) {
            BDialog.showSignOut(this, new AlertDialogListener() {
                @Override
                public void onPositive() {
                    signOut();
                }

                @Override
                public void onNegative() {

                }
            });
        }
        else if(id == R.id.nav_delete) {
            BDialog.showDeleteAccount(this, new AlertDialogListener() {
                @Override
                public void onPositive() {
                    ApiHelper.getInstance(getApplicationContext()).deleteUser(
                            new LoginRequest(selfToken)).enqueue(new Callback<BetchaResponse>() {
                        @Override
                        public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                            if (response.code() != 200) {
                                Log.d("Response Code",String.valueOf(response.code()));
                                Log.d("Response Message",String.valueOf(response.message()));
                                BToast.makeError(BetchaActivity.this, getString(R.string.delete_account_error));
                                return;
                            }
                            else {
//                                Intent myIntent = new Intent(BetchaActivity.this, LoginActivity.class);
                                Log.d("Body Response", response.toString());
                                Log.d("Message", response.message());
                                if(response.body().getSelfToken() != null) {
                                    Log.d("Callback Token", response.body().getSelfToken());
                                }
                                BToast.makeSuccess(BetchaActivity.this, getString(R.string.delete_account_success));
                                signOut();
//                                startActivity(myIntent);
//                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<BetchaResponse> call, Throwable t) {
                            BToast.makeServerError(BetchaActivity.this);
                        }
                    });
                }

                @Override
                public void onNegative() {

                }
            });
        }
        else if(id == R.id.nav_create_bet) {
            Intent myIntent = new Intent(this, CreateBetActivity.class);
            myIntent.putExtra("selfToken", selfToken);
            startActivity(myIntent);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent myIntent = new Intent(BetchaActivity.this, ProfileActivity.class);
            startActivity(myIntent);
            overridePendingTransition(R.animator.enter_activity, R.animator.exit_activity);

        } else if (id == R.id.nav_social_feed) {
            Intent myIntent = new Intent(BetchaActivity.this, NewsFeedActivity.class);
            startActivity(myIntent);
            overridePendingTransition(R.animator.enter_activity, R.animator.exit_activity);
        } else if (id == R.id.nav_bets) {
            Intent myIntent = new Intent(BetchaActivity.this, MyBetsActivity.class);
            startActivity(myIntent);
            overridePendingTransition(R.animator.enter_activity, R.animator.exit_activity);
        } else if (id == R.id.nav_friends) {
            Intent myIntent = new Intent(BetchaActivity.this, FriendsActivity.class);
            startActivity(myIntent);
            overridePendingTransition(R.animator.enter_activity, R.animator.exit_activity);
        } else if (id == R.id.nav_payments) {
            Intent myIntent = new Intent(BetchaActivity.this, PaymentActivity.class);
            startActivity(myIntent);
            overridePendingTransition(R.animator.enter_activity, R.animator.exit_activity);
        } else if (id == R.id.nav_bug_report) {
            Intent myIntent = new Intent(BetchaActivity.this, FeedbackActivity.class);
            startActivity(myIntent);
            overridePendingTransition(R.animator.enter_activity, R.animator.exit_activity);
        } else if (id == R.id.nav_notifs) {
            Intent myIntent = new Intent(BetchaActivity.this, NotificationsActivity.class);
            startActivity(myIntent);
            overridePendingTransition(R.animator.enter_activity, R.animator.exit_activity);
        }
        finish();
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    public void signOut() {
        Auth.GoogleSignInApi.signOut(apiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Intent myIntent = new Intent(
                                BetchaActivity.this, LoginActivity.class);
                        BToast.makeInformation(BetchaActivity.this, getString(R.string.sign_out_success));
                        startActivity(myIntent);
                        finish();
                    }
                });

    }
}
