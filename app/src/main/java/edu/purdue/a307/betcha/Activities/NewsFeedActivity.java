package edu.purdue.a307.betcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

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
        Call<PublicFeedResponse> call = service.getPublicFeed();

        List<Bet> feed = null;

        call.enqueue(new Callback<PublicFeedResponse>() {
            @Override
            public void onResponse(Call<PublicFeedResponse> call, Response<PublicFeedResponse> response) {
                int statusCode = response.code();
                PublicFeedResponse feed = response.body();

                if(response.isSuccessful()) {
                    // TODO - Update with newsfeed adapter
                    mAdapter = new NewsFeedAdapter(feed.getBets());
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    try {
                        Log.d("api", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<PublicFeedResponse> call, Throwable t) {
                Log.e("News Feed API", "Failed to get content from server for NewsFeed", t);
            }
        });


    }

    protected int getLayoutResource() { return R.layout.activity_news_feed; }
}
