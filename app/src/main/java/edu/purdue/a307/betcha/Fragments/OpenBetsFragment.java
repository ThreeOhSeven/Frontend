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

import edu.purdue.a307.betcha.Activities.CreateBetActivity;
import edu.purdue.a307.betcha.Activities.MyBetsActivity;
import edu.purdue.a307.betcha.Adapters.BetAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.Models.BetInformations;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenBetsFragment extends Fragment implements OnPageSelectedListener {

    RecyclerView recyclerView;
    ArrayList<BetInformation> bets;
    BetAdapter betAdapter;
    FloatingActionButton createBet;
    String selfToken;


    public OpenBetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_open_bets, container, false);
        bets = new ArrayList<BetInformation>();
        selfToken = SharedPrefsHelper.getSelfToken(getContext());
//        fillList();
        createBet = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        createBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Make this create bets activity
                Intent myIntent = new Intent(getActivity(), CreateBetActivity.class);
                myIntent.putExtra("selfToken", selfToken);
                startActivity(myIntent);
            }
        });
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerBets);
        Log.d("Self Token", selfToken);
        onPageSelected();
        return view;
    }

    @Override
    public void onPageSelected() {
//        ApiHelper.getInstance(getContext()).getUserBets(new LoginRequest(selfToken)).enqueue(new Callback<BetInformations>() {
//            @Override
//            public void onResponse(Call<BetInformations> call, Response<BetInformations> response) {
//                if(response.code() != 200) {
//                    Log.d("AUTH ERROR", String.valueOf(response.code()));
//                    Toast.makeText(getContext(), "Unable to get bets",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Log.d("Bets size", String.valueOf(response.body().getMyBets().size()));
//                bets = response.body().getMyBets();
//                betAdapter = new BetAdapter(getActivity(), bets,selfToken);
//                recyclerView.setAdapter(betAdapter);
//                recyclerView.invalidate();
//                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                betAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure(Call<BetInformations> call, Throwable t) {
//                Log.d("COMPLETE FAIL", "FAiled");
//            }
//        });


        bets = new ArrayList<>();
        bets.add(new BetInformation("Kyle", "10", "SuperBowl","10"));
        bets.add(new BetInformation("Sidd", "20", "World Series","7"));
        bets.add(new BetInformation("Peter", "30", "NBA Finals","6"));
        bets.add(new BetInformation("Noah", "5", "Stanley Cup","9"));
        bets.add(new BetInformation("Kush", "1", "UEFA Champions League","2"));
        betAdapter = new BetAdapter(getActivity(), bets,selfToken);
        recyclerView.setAdapter(betAdapter);
        recyclerView.invalidate();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        betAdapter.notifyDataSetChanged();
    }
}
