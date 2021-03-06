package edu.purdue.a307.betcha.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.purdue.a307.betcha.Adapters.InviteFriendsAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.FriendItem;
import edu.purdue.a307.betcha.Models.FriendItems;
import edu.purdue.a307.betcha.Models.InviteFriendsObj;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.Models.RejectBetRequest;
import edu.purdue.a307.betcha.Models.SendBetRequest;
import edu.purdue.a307.betcha.Models.User;
import edu.purdue.a307.betcha.Models.Users;
import edu.purdue.a307.betcha.R;
import edu.purdue.a307.betcha.Services.EditBetSyncService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvitePeepsActivity extends AppCompatActivity {

    @BindView(R.id.invitePeepsBtn)
    Button invitePeepsBtn;

    @BindView(R.id.inviteRecycler)
    RecyclerView inviteRec;

    ArrayList<InviteFriendsObj> invites;
    InviteFriendsAdapter adapter;

    int type;
    String betID;

    LocalBroadcastManager mLocalBroadcastManager;

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("edu.purdue.a307.betcha.close")){
                Intent myIntent = getIntent();
                myIntent.putExtra("usersList", intent.getStringExtra("users"));
                InvitePeepsActivity.this.setResult(RESULT_OK, myIntent);
                finish();
                return;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_peeps);
        ButterKnife.bind(this);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        type = getIntent().getIntExtra("type",2);

        betID = getIntent().getStringExtra("betID");

        if(type == 0) {
            ApiHelper.getInstance(this).getFriends(new LoginRequest(SharedPrefsHelper.getSelfToken(this))).enqueue(new Callback<FriendItems>() {
                @Override
                public void onResponse(Call<FriendItems> call, Response<FriendItems> response) {
                    if(response.code() != 200) {
                        BToast.makeFriendsError(InvitePeepsActivity.this);
                        return;
                    }
                    ArrayList<FriendItem> items = response.body().getFriends_obj();
                    invites = new ArrayList<InviteFriendsObj>();
                    for(FriendItem item : items) {
                        invites.add(new InviteFriendsObj(item.getFriend()));
                    }
                    adapter = new InviteFriendsAdapter(getApplicationContext(), invites);
                    inviteRec.setAdapter(adapter);
                    inviteRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }

                @Override
                public void onFailure(Call<FriendItems> call, Throwable t) {
                    BToast.makeServerError(InvitePeepsActivity.this);
                }
            });
        }

        else if(type == 1) {
            Log.d("BET ID", betID);
            ApiHelper.getInstance(this).getFriendsNotInBet(new RejectBetRequest(betID,SharedPrefsHelper.getSelfToken(this))).enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if(response.code() != 200) {
                        BToast.makeFriendsError(InvitePeepsActivity.this);
                        return;
                    }
                    List<User> items = response.body().users;
                    invites = new ArrayList<InviteFriendsObj>();
                    for(User user : items) {
                        invites.add(new InviteFriendsObj(user));
                    }
                    adapter = new InviteFriendsAdapter(getApplicationContext(), invites);
                    inviteRec.setAdapter(adapter);
                    inviteRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    BToast.makeServerError(InvitePeepsActivity.this);
                }
            });
        }

        invitePeepsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<User> selectedUsers = new ArrayList<User>();
                ArrayList<InviteFriendsObj> currentItems = (ArrayList<InviteFriendsObj>)adapter.items;
                for (InviteFriendsObj obj: currentItems) {
                    if(obj.isChecked) {
                        selectedUsers.add(obj.friend);
                    }
                }
                Users users = new Users(selectedUsers);
                Intent myIntent = getIntent();
                myIntent.putExtra("usersList",new Gson().toJson(users));
                setResult(RESULT_OK, myIntent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("edu.purdue.a307.betcha.close");
        registerReceiver(receiver, intentFilter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
