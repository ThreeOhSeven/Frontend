package edu.purdue.a307.betcha.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by kyleohanian on 10/18/17.
 */

public class SectionsAdapter extends FragmentStatePagerAdapter {

    String[] titles;
    Fragment[] fragments;

    public SectionsAdapter(FragmentManager fm, Fragment[] fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return fragments.length;
    }
}
