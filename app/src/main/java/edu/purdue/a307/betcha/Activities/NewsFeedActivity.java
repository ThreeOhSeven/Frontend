package edu.purdue.a307.betcha.Activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import edu.purdue.a307.betcha.Fragments.PrivateFeedFragment;
import edu.purdue.a307.betcha.Fragments.PublicFeedFragment;


public class NewsFeedActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    protected Fragment[] getFragmentsList() {
        return new Fragment[]{new PublicFeedFragment(), new PrivateFeedFragment()};
    }

    @Override
    protected String[] getStringsList() {
        return new String[]{"Public Feed", "Private Feed"};
    }

}
