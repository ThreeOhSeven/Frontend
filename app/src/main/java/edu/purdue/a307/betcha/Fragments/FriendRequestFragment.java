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
public class FriendRequestFragment extends Fragment implements OnPageSelectedListener {


    public FriendRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_friend_requests, container, false);
        return v;
    }

    @Override
    public void onPageSelected() {

    }
}
