package edu.purdue.a307.betcha.Activities;

import android.os.Bundle;

import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.R;

public class HomeActivity extends BetchaActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BToast.makeSuccess(this, "Logged in as: " + SharedPrefsHelper.getAccountInformation(
                getApplicationContext()).getEmail());

    }

    protected int getLayoutResource() { return R.layout.activity_home; }
}
