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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import edu.purdue.a307.betcha.R;

public abstract class BetchaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    GoogleApiClient apiClient;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betcha);

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
            signOut();
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
            Intent myIntent = new Intent(BetchaActivity.this, BetsActivity.class);
            startActivity(myIntent);
            overridePendingTransition(R.animator.enter_activity, R.animator.exit_activity);
        } else if (id == R.id.nav_friends) {
            Intent myIntent = new Intent(BetchaActivity.this, FriendsActivity.class);
            startActivity(myIntent);
            overridePendingTransition(R.animator.enter_activity, R.animator.exit_activity);
        }
//
//        } else if (id == R.id.nav_gen_settings) {
//
//        } else if (id == R.id.nav_bug_report) {
//
//        }
        else {
            return true;
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
                        startActivity(myIntent);
                        finish();
                    }
                });

    }
}
