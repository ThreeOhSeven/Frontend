package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.purdue.a307.betcha.Adapters.FriendAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Enums.AdapterType;
import edu.purdue.a307.betcha.Fragments.InvitePeepsFragment;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.Models.BetInformationRequest;
import edu.purdue.a307.betcha.Models.BetUpdateRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.CreateBetResponse;
import edu.purdue.a307.betcha.Models.FriendItem;
import edu.purdue.a307.betcha.Models.SendBetRequest;
import edu.purdue.a307.betcha.Models.User;
import edu.purdue.a307.betcha.Models.Users;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by kushagra on 10/2/17.
 */

public class EditBetActivity extends BetchaActivity {


    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.amount)
    EditText amount;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.maxUsers)
    EditText maxUsers;
    @BindView(R.id.locked)
    CheckBox locked;
    @BindView(R.id.sideA)
    EditText sideA;
    @BindView(R.id.sideB)
    EditText sideB;
    @BindView(R.id.createBetBtn)
    FloatingActionButton createButton;
    @BindView(R.id.inviteButton)
    Button inviteButton;
    @BindView(R.id.betUsers)
    RecyclerView betUsers;


    FriendAdapter friendAdapter;
    Bet bet;

    String selfToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selfToken = SharedPrefsHelper.getSelfToken(this);

        bet = new Gson().fromJson(getIntent().getStringExtra("jsonObj"), Bet.class);

        ButterKnife.bind(this);

        // TODO - Change to be the bet passed through the intent
        try {
            title.setText(bet.getTitle());
        } catch (Exception e) {
            //Do nothing
        }
        try {
            amount.setText(Double.toString(bet.getAmount()));
        } catch (Exception e) {
            //Do nothing
        }
        try {
            description.setText(bet.getDescription());
        } catch (Exception e) {
            //Do nothing
        }
        try {
            maxUsers.setText(bet.getMaxUsers());
        } catch (Exception e) {
            //Do nothing
        }
        try {
            sideA.setText(bet.getSideA());
        } catch (Exception e) {
            //Do nothing
        }
        try {
            sideB.setText(bet.getSideB());
        } catch (Exception e) {
            //Do nothing
        }
        try {
            if(bet.isLocked()) {
                locked.setChecked(true);
            }
            else
                locked.setChecked(false);
        } catch (Exception e) {
            //Do nothing
        }

        ArrayList<FriendItem> friends = new ArrayList<FriendItem>();
        try {

            for(User user : bet.users) {
                friends.add(new FriendItem("1", user));
            }
        } catch (Exception e) {
            //Do nothing
        }



        friendAdapter = new FriendAdapter(this, friends, SharedPrefsHelper.getSelfToken(this), AdapterType.FRIENDS_BETS);
        betUsers.setAdapter(friendAdapter);
        betUsers.setLayoutManager(new LinearLayoutManager(this));


        inviteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EditBetActivity.this, InvitePeepsActivity.class);
                myIntent.putExtra("type", 1);
                myIntent.putExtra("betID",String.valueOf(bet.getId()));
                startActivityForResult(myIntent, 1000);
            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1000) : {
                if (resultCode == RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    Users users = new Gson().fromJson(data.getStringExtra("usersList"), Users.class);
                    Log.d("Users Length:", String.valueOf(users.users.size()));
                    for(User user: users.users) {
                        friendAdapter.items.add(new FriendItem("",user));
                    }
                    friendAdapter.notifyDataSetChanged();
                }
                break;
            }
        }
    }

    protected int getLayoutResource() { return R.layout.activity_create_bet; }

    @OnClick(R.id.createBetBtn)
    public void create() {
        final BetUpdateRequest betInformationRequest = new BetUpdateRequest();
        betInformationRequest.amount = amount.getText().toString();
        betInformationRequest.title = title.getText().toString();
        betInformationRequest.description = description.getText().toString();
        betInformationRequest.maxUsers = maxUsers.getText().toString();
        betInformationRequest.betId = bet.getId();

        if(locked.isChecked()) {
            betInformationRequest.locked = true;
        } else {
            betInformationRequest.locked = false;
        }

        betInformationRequest.sideA = sideA.getText().toString();
        betInformationRequest.sideB = sideB.getText().toString();
        betInformationRequest.authToken = selfToken;
        if(maxUsers.getText().toString() == null ||
                maxUsers.getText().toString().length() <= 0) {
            return;
        }
        if(friendAdapter != null && Integer.parseInt(maxUsers.getText().toString()) < friendAdapter.items.size()) {
            BToast.makeError(EditBetActivity.this, getString(R.string.too_many_users));
            return;
        }
        ApiHelper.getInstance(getApplicationContext()).
                updateBet(betInformationRequest).enqueue(new Callback<CreateBetResponse>() {
            @Override
            public void onResponse(Call<CreateBetResponse> call, Response<CreateBetResponse> response) {
                if (response.code() != 200) {
                    Log.d("Response Code",String.valueOf(response.code()));
                    Log.d("Response Message",String.valueOf(response.message()));
                    BToast.makeError(EditBetActivity.this, getString(R.string.bet_editing_error));
                    return;
                }
                else {
                    BToast.makeSuccess(EditBetActivity.this, getString(R.string.bet_editing_success));
                    if(friendAdapter == null) {
                        Intent intent = new Intent(EditBetActivity.this, MyBetsActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    }
                    for(final FriendItem item: friendAdapter.items) {
                        ApiHelper.getInstance(getApplicationContext()).sendBet(
                                new SendBetRequest(item.getFriend().getId(),Integer.toString(bet.getId()),
                                        selfToken)).enqueue(new Callback<BetchaResponse>() {
                            @Override
                            public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                if(response.code() != 200) {
                                    BToast.makeError(EditBetActivity.this, "Unable to add user: " + item.getFriend().getEmail());
                                    return;
                                }
                            }
                            @Override
                            public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                BToast.makeServerError(EditBetActivity.this);
                            }
                        });
                    }

                    Intent intent = new Intent(EditBetActivity.this, MyBetsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<CreateBetResponse> call, Throwable t) {
                BToast.makeServerError(EditBetActivity.this);
            }
        });
    }

    @OnClick(R.id.betUsers)
    public void onBetUsersClick() {
        InvitePeepsFragment ipf = new InvitePeepsFragment();
        ipf.show(getSupportFragmentManager(),"fragment");
    }
}