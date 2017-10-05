package edu.purdue.a307.betcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import edu.purdue.a307.betcha.Adapters.BetAdapter;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.R;

public class BetActivity extends BetchaActivity {

    RecyclerView recyclerView;
    ArrayList<BetInformation> bets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bets = new ArrayList<BetInformation>();
        fillList();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerBets);
        BetAdapter betAdapter = new BetAdapter(this, bets);
        recyclerView.setAdapter(betAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    protected int getLayoutResource() {
        return R.layout.activity_bet;
    }


    /*
        *
        *                           DEBUG TESTING
        *
    */

    public void fillList() {
        bets.add(new BetInformation("Kyle", "10", "Superbowl", "5.00"));
        bets.add(new BetInformation("Kush", "7", "Stanley Cup", "51.00"));
        bets.add(new BetInformation("Peter", "2", "World Series", "30.00"));
        bets.add(new BetInformation("Sidd", "6", "NBA Finals", "40.00"));
        bets.add(new BetInformation("Noah", "4", "UEFA Champions League", "10.00"));
    }
}
