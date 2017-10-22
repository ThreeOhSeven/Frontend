package edu.purdue.a307.betcha.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import edu.purdue.a307.betcha.Activities.NewsFeedActivity;
import edu.purdue.a307.betcha.Adapters.NewsFeedAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Api.BetchaApi;
import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.PublicFeedResponse;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicFeedFragment extends Fragment implements OnPageSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public PublicFeedFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_public_feed, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_feed_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        onPageSelected();
        return view;
    }

    @Override
    public void onPageSelected() {
        BetchaApi service =  ApiHelper.getInstance(getContext());
        Call<PublicFeedResponse> call = service.getPublicFeed();


        call.enqueue(new Callback<PublicFeedResponse>() {
            @Override
            public void onResponse(Call<PublicFeedResponse> call, Response<PublicFeedResponse> response) {
                int statusCode = response.code();
                PublicFeedResponse feed = response.body();

                if(response.isSuccessful()) {
                    // TODO - Update with newsfeed adapter
                    mAdapter = new NewsFeedAdapter(getActivity(), feed.getBets());
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
}
