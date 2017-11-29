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

import edu.purdue.a307.betcha.Adapters.NewsFeedAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Api.BetchaApi;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.Models.Bets;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivateFeedFragment extends Fragment implements OnPageSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String selfTokenFA;


    public PrivateFeedFragment() {
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
        BetchaApi service = ApiHelper.getInstance(getContext());
        selfTokenFA = SharedPrefsHelper.getSelfToken(getContext());
        Call<Bets> call = service.getPrivateFeed(new LoginRequest(selfTokenFA));



        call.enqueue(new Callback<Bets>() {
            @Override
            public void onResponse(Call<Bets> call, Response<Bets> response) {
                int statusCode = response.code();
                Bets feed = response.body();

                if (response.isSuccessful()) {
                    mAdapter = new NewsFeedAdapter(getActivity(), feed.getBets(), selfTokenFA, 1);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    BToast.makeBetsError(getActivity());
                }
            }

            @Override
            public void onFailure(Call<Bets> call, Throwable t) {
                Log.e("News Feed API", "Failed to get content from server for NewsFeed", t);
                BToast.makeServerError(getActivity());
            }
        });

    }
}
