package edu.purdue.a307.betcha.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.purdue.a307.betcha.Adapters.FriendAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Enums.AdapterType;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.Models.FriendItem;
import edu.purdue.a307.betcha.Models.FriendItems;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendRequestFragment extends Fragment implements OnPageSelectedListener {

    @BindView(R.id.recyclerFriendRequests)
    RecyclerView requests;


    public FriendRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_friend_requests, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onPageSelected() {
        final String token = SharedPrefsHelper.getSelfToken(getContext());
        ApiHelper.getInstance(getContext()).getFriendRequests(new LoginRequest(token)).enqueue(new Callback<FriendItems>() {
            @Override
            public void onResponse(Call<FriendItems> call, Response<FriendItems> response) {
                if(response.code() != 200) {
                    BToast.makeShort(getContext(), "This failed");
                    return;
                }
                ArrayList<FriendItem> friends = new ArrayList<FriendItem>();
                for(FriendItem friendItem : response.body().getFriends_obj()) {
                    if(Integer.parseInt(friendItem.getStatus()) == 0) {
                        friends.add(friendItem);
                    }
                }
                FriendAdapter adapter = new FriendAdapter(getActivity(), friends,
                        token, AdapterType.FRIENDS_LIST);

                requests.setAdapter(adapter);
                requests.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onFailure(Call<FriendItems> call, Throwable t) {

            }
        });
    }
}
