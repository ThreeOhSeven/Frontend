package edu.purdue.a307.betcha.Activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import edu.purdue.a307.betcha.Fragments.ConnectAccountFragment;
import edu.purdue.a307.betcha.Fragments.PaymentFragment;
import edu.purdue.a307.betcha.Fragments.PayoutFragment;

public class PaymentActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment[] getFragmentsList() {
        return new Fragment[]{new ConnectAccountFragment(), new PaymentFragment(), new PayoutFragment()};
    }

    @Override
    protected String[] getStringsList() {
        return new String[]{"Connect Account", "Make Payment", "Payout"};
    }
}
