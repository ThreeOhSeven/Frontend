package edu.purdue.a307.betcha.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.purdue.a307.betcha.Fragments.BetInvitesFragment;
import edu.purdue.a307.betcha.Fragments.CompletedBetsFragment;
import edu.purdue.a307.betcha.Fragments.OpenBetsFragment;
import edu.purdue.a307.betcha.Fragments.PrivateFeedFragment;
import edu.purdue.a307.betcha.Fragments.PublicFeedFragment;

/**
 * Created by kyleohanian on 10/13/17.
 */

public class MyBetsPagerAdapter extends FragmentStatePagerAdapter {

    int tabs;

    public MyBetsPagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.tabs = tabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new OpenBetsFragment();
            case 1:
                return new CompletedBetsFragment();
            case 2:
                return new BetInvitesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}