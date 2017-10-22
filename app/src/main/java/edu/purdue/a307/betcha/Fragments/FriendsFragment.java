package edu.purdue.a307.betcha.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.purdue.a307.betcha.Activities.FriendsActivity;
import edu.purdue.a307.betcha.Activities.SearchFriendsActivity;
import edu.purdue.a307.betcha.Adapters.FriendAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.FriendItem;
import edu.purdue.a307.betcha.Models.FriendItems;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.Models.UserEmailRequest;
import edu.purdue.a307.betcha.Models.UserID;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment implements OnPageSelectedListener {

    @BindView(R.id.recyclerFriends)
    RecyclerView recyclerView;
    List<FriendItem> friends;
    FloatingActionButton addFriend;
    FriendAdapter friendAdapter;
    String selfTokenFA;
    View v;


    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_friends, container, false);
        ButterKnife.bind(this,v);
        addFriend = (FloatingActionButton)v.findViewById(R.id.floatingActionButton);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), SearchFriendsActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
        onPageSelected();
        return v;
    }

    @Override
    public void onPageSelected() {
        selfTokenFA = SharedPrefsHelper.getSelfToken(getContext());
        friends = new ArrayList<FriendItem>();
    }
}
