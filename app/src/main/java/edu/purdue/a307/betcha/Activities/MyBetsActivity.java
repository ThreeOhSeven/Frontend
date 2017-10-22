package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.a307.betcha.Adapters.BetAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Fragments.BetInvitesFragment;
import edu.purdue.a307.betcha.Fragments.CompletedBetsFragment;
import edu.purdue.a307.betcha.Fragments.OpenBetsFragment;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.BetInformations;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBetsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Fragment[] getFragmentsList() {
        return new Fragment[]{new OpenBetsFragment(), new CompletedBetsFragment(), new BetInvitesFragment()};
    }

    @Override
    protected String[] getStringsList() {
        return new String[]{"Open Bets","Completed Bets", "Invites"};
    }
}
