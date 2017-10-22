package edu.purdue.a307.betcha.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.io.IOException;
import edu.purdue.a307.betcha.Fragments.PrivateFeedFragment;
import edu.purdue.a307.betcha.Fragments.PublicFeedFragment;
import edu.purdue.a307.betcha.Models.PublicFeedResponse;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cloudinary.Api;

import java.io.IOException;
import java.util.List;

import edu.purdue.a307.betcha.Adapters.NewsFeedAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Api.BetchaApi;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.PublicFeedResponse;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFeedActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    protected Fragment[] getFragmentsList() {
        return new Fragment[]{new PublicFeedFragment(), new PrivateFeedFragment()};
    }

    @Override
    protected String[] getStringsList() {
        return new String[]{"Public Feed", "Private Feed"};
    }

}
