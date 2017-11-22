package edu.purdue.a307.betcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.AddFriendRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.Models.UserEmailRequest;
import edu.purdue.a307.betcha.Models.UserID;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFriendsActivity extends BetchaActivity {


    @BindView(R.id.searchQuery)
    EditText searchText;

    @BindView(R.id.searchButton)
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = searchText.getText().toString();
                if (name == null || name.length() <= 0) {
                    Toast.makeText(getApplicationContext(), "Please type in an email", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO: Handle action for sending friend request
                    UserEmailRequest uer = new UserEmailRequest();
                    uer.authToken = SharedPrefsHelper.getSelfToken(getApplicationContext());
                    uer.email = name;
                    ApiHelper.getInstance(getApplicationContext()).getIDByUser(
                            uer).enqueue(new Callback<UserID>() {
                        @Override
                        public void onResponse(Call<UserID> call, Response<UserID> response) {
                            if(response.code() != 200) {
                                Log.d("AUTH ERROR", String.valueOf(response.code()));
                                BToast.makeError(SearchFriendsActivity.this, getString(R.string.find_friend_error));
                                return;
                            }
                            String id = response.body().getId();
                            Log.d("ID", id);
                            ApiHelper.getInstance(getApplicationContext()).addFriend(
                                    new AddFriendRequest(SharedPrefsHelper.getSelfToken(getApplicationContext()),id)).enqueue(new Callback<BetchaResponse>() {
                                @Override
                                public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                    if(response.code() != 200) {
                                        Log.d("AUTH ERROR", String.valueOf(response.message()));
                                        BToast.makeError(SearchFriendsActivity.this, getString(R.string.send_friend_request_error));
                                        return;
                                    }
                                    BToast.makeSuccess(SearchFriendsActivity.this, getString(R.string.send_friend_request_success));
                                }

                                @Override
                                public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                    BToast.makeError(SearchFriendsActivity.this, getString(R.string.send_friend_request_error));
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<UserID> call, Throwable t) {
                            BToast.makeError(SearchFriendsActivity.this, getString(R.string.find_friend_error));
                        }
                    });
                }
            }
        });


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_search_friends;
    }
}
