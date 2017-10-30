package edu.purdue.a307.betcha.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.Models.FriendItems;
import edu.purdue.a307.betcha.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendRequestFragment extends Fragment implements OnPageSelectedListener {

    @BindView(R.id.recyclerFriendRequests)
    RecyclerView requests;
    List<FriendItems> friends;


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
