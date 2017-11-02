package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.purdue.a307.betcha.Adapters.InviteFriendsAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.FriendItem;
import edu.purdue.a307.betcha.Models.FriendItems;
import edu.purdue.a307.betcha.Models.InviteFriendsObj;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.Models.User;
import edu.purdue.a307.betcha.Models.Users;
import edu.purdue.a307.betcha.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_peeps);
        ButterKnife.bind(this);

        ApiHelper.getInstance(this).getFriends(new LoginRequest(SharedPrefsHelper.getSelfToken(this))).enqueue(new Callback<FriendItems>() {
            @Override
            public void onResponse(Call<FriendItems> call, Response<FriendItems> response) {
                if(response.code() != 200) {
                    BToast.makeShort(getApplicationContext(),"Friends Thing didn't work");
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
                BToast.makeShort(getApplicationContext(),"Friends Thing didn't work");
            }
        });

        invitePeepsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<InviteFriendsObj> currentItems = (ArrayList<InviteFriendsObj>)adapter.items;
                ArrayList<User> selectedUsers = new ArrayList<User>();
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
}
