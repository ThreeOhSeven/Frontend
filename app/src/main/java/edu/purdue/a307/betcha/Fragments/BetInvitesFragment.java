package edu.purdue.a307.betcha.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.a307.betcha.Activities.CreateBetActivity;
import edu.purdue.a307.betcha.Adapters.BetAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.Bets;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BetInvitesFragment extends Fragment implements OnPageSelectedListener {


    RecyclerView recyclerView;
    List<Bet> bets;
    BetAdapter betAdapter;
    FloatingActionButton createBet;
    String selfToken;


    public BetInvitesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bet_invites, container, false);
        bets = new ArrayList<Bet>();
        selfToken = SharedPrefsHelper.getSelfToken(getContext());
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerBetInvites);
        Log.d("Self Token", selfToken);
        onPageSelected();
        return view;
    }

    @Override
    public void onPageSelected() {
        ApiHelper.getInstance(getContext()).getMyPendingBets(new LoginRequest(selfToken)).enqueue(new Callback<Bets>() {
            @Override
            public void onResponse(Call<Bets> call, Response<Bets> response) {
                if(response.code() != 200) {
                    Log.d("AUTH ERROR", String.valueOf(response.code()));
                    Toast.makeText(getContext(), "Unable to get bets",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("Bets size", String.valueOf(response.body().getBets().size()));
                bets = response.body().getBets();
                betAdapter = new BetAdapter(getActivity(), bets,selfToken);
                recyclerView.setAdapter(betAdapter);
                recyclerView.invalidate();
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                betAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Bets> call, Throwable t) {
                Log.d("COMPLETE FAIL", "FAiled");
            }
        });
    }
}
