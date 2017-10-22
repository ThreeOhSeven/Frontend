package edu.purdue.a307.betcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import edu.purdue.a307.betcha.Models.BetInformation;
import edu.purdue.a307.betcha.R;

public class EditBetActivity extends BetchaActivity {

    BetInformation betInformation;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.amount)
    TextView amount;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.maxUsers)
    TextView maxUsers;

    @BindView(R.id.locked)
    CheckBox locked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        betInformation = new Gson().fromJson(getIntent().getStringExtra("jsonObj"),
                BetInformation.class);

        // TODO: Fill data and other stuff
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_create_bet;
    }
}
