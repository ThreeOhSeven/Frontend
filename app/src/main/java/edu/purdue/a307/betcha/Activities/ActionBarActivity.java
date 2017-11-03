package edu.purdue.a307.betcha.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.purdue.a307.betcha.Adapters.SectionsAdapter;
import edu.purdue.a307.betcha.Listeners.OnListChangedListener;
import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.R;

/**
 * Created by kyleohanian on 10/18/17.
 */

public abstract class ActionBarActivity extends BetchaActivity {


    private SectionsAdapter mSectionsPagerAdapter;

    @BindView(R.id.container)
    public ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    public void setStuffUp() {
        mSectionsPagerAdapter = new SectionsAdapter(getSupportFragmentManager(), getFragmentsList(), getStringsList());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {}

            @Override
            public void onPageSelected(int i) {
                OnPageSelectedListener frag = (OnPageSelectedListener)mSectionsPagerAdapter.getItem(i);
                frag.onPageSelected();
            }

            @Override
            public void onPageScrollStateChanged(int i) {}
        });
    }

    protected int getLayoutResource() {
        return R.layout.activity_action_bar;
    }

    protected abstract Fragment[] getFragmentsList();

    protected abstract String[] getStringsList();

    @Override
    public void onStart() {
        super.onStart();
        setStuffUp();
    }
}
