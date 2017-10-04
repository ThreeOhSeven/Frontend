package edu.purdue.a307.betcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cloudinary.Api;

import java.util.List;

import edu.purdue.a307.betcha.Adapters.NewsFeedAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Api.BetchaApi;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedActivity extends BetchaActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = (RecyclerView) findViewById(R.id.news_feed_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        BetchaApi service =  ApiHelper.getInstance(this);
        Call<List<Bet>> call = service.getPublicFeed();

        List<Bet> feed = null;

        call.enqueue(new Callback<List<Bet>>() {
            @Override
            public void onResponse(Call<List<Bet>> call, Response<List<Bet>> response) {
                int statusCode = response.code();
                List<Bet> feed = response.body();
            }

            @Override
            public void onFailure(Call<List<Bet>> call, Throwable t) {
                Log.e("News Feed API", "Failed to get content from server for NewsFeed", t);
            }
        });

        // TODO - Update with newsfeed adapter
        mAdapter = new NewsFeedAdapter(feed);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected int getLayoutResource() { return R.layout.activity_news_feed; }
}
