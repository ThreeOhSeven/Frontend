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
import com.google.gson.internal.Excluder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.purdue.a307.betcha.Adapters.FriendAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Enums.AdapterType;
import edu.purdue.a307.betcha.Fragments.InvitePeepsFragment;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.BetInformationRequest;
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

public class CreateBetActivity extends BetchaActivity {


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


    FriendAdapter friendAdapter = null;

    String selfToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View rootView = inflater.inflate(R.layout.fragment_bets, container, false);
        //textView = rootView.findViewById(R.id.nav_bets);
        //textView.setText(getResources().getString(R.string.bets_str));
        //textView = (TextView) findViewById(R.id.nav_bets);
        selfToken = SharedPrefsHelper.getSelfToken(this);

        ButterKnife.bind(this);

        // TODO - Change to be the bet passed through the intent
        title.setText("World Series");
        amount.setText("5");
        description.setText("This is for all the marbles!!!!");
        maxUsers.setText("3");
        sideA.setText("Dodgers");
        sideB.setText("Astros");

        inviteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CreateBetActivity.this, InvitePeepsActivity.class);
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
                    String returnValue = data.getStringExtra("usersList");
                    Users users = new Gson().fromJson(returnValue, Users.class);
                    ArrayList<FriendItem> friends = new ArrayList<>();
                    for(User user: users.users) {
                        friends.add(new FriendItem("", user));
                    }
                    friendAdapter = new FriendAdapter(this, friends, SharedPrefsHelper.getSelfToken(this), AdapterType.FRIENDS_BETS);
                    betUsers.setAdapter(friendAdapter);
                    betUsers.setLayoutManager(new LinearLayoutManager(this));
                }
                break;
            }
        }
    }

    protected int getLayoutResource() { return R.layout.activity_create_bet; }

    @OnClick(R.id.createBetBtn)
    public void create() {
        final BetInformationRequest betInformationRequest = new BetInformationRequest();
        betInformationRequest.amount = amount.getText().toString();
        betInformationRequest.title = title.getText().toString();
        betInformationRequest.description = description.getText().toString();
        betInformationRequest.maxUsers = maxUsers.getText().toString();

        if(locked.isChecked()) {
            betInformationRequest.locked = true;
        } else {
            betInformationRequest.locked = false;
        }

        betInformationRequest.sideA = sideA.getText().toString();
        betInformationRequest.sideB = sideB.getText().toString();
        betInformationRequest.authToken = selfToken;
        ApiHelper.getInstance(getApplicationContext()).
                createBet(betInformationRequest).enqueue(new Callback<CreateBetResponse>() {
            @Override
            public void onResponse(Call<CreateBetResponse> call, Response<CreateBetResponse> response) {
                if (response.code() != 200) {
                    Log.d("Response Code",String.valueOf(response.code()));
                    Log.d("Response Message",String.valueOf(response.message()));
                    Toast.makeText(getApplicationContext(), "Error in creating bet",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    try{
                        if(friendAdapter.items.size() +1 > Integer.parseInt(betInformationRequest.getMaxUsers())) {
                            BToast.makeShort(getApplicationContext(), "Cannot exceed max users");
                            finish();
                        }

                        for(FriendItem item: friendAdapter.items) {
                            ApiHelper.getInstance(getApplicationContext()).sendBet(
                                    new SendBetRequest(item.getFriend().getId(),response.body().getId(),
                                            selfToken)).enqueue(new Callback<BetchaResponse>() {
                                @Override
                                public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                    if(response.code() != 200) {
                                        BToast.makeShort(getApplicationContext(), "Adding bet user did not work");
                                    }

                                }

                                @Override
                                public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                    BToast.makeShort(getApplicationContext(), "Adding bet user did not work");
                                }
                            });
                        }
                    } catch (Exception e) {
                        // Do nothing
                    }


                    finish();
                }
            }

            @Override
            public void onFailure(Call<CreateBetResponse> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.betUsers)
    public void onBetUsersClick() {
        InvitePeepsFragment ipf = new InvitePeepsFragment();
        ipf.show(getSupportFragmentManager(),"fragment");
    }
}