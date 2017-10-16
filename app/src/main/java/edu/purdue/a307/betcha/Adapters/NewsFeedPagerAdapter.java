package edu.purdue.a307.betcha.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.purdue.a307.betcha.Fragments.PrivateFeedFragment;
import edu.purdue.a307.betcha.Fragments.PublicFeedFragment;

/**
 * Created by kyleohanian on 10/13/17.
 */

public class NewsFeedPagerAdapter extends FragmentStatePagerAdapter {

    int tabs;

    public NewsFeedPagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.tabs = tabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new PublicFeedFragment();
            case 1:
                return new PrivateFeedFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
