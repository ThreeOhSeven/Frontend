package edu.purdue.a307.betcha.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivateFeedFragment extends Fragment implements OnPageSelectedListener {


    public PrivateFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_private_feed, container, false);
    }

    @Override
    public void onPageSelected() {

    }
}
