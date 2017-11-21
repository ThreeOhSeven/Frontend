package edu.purdue.a307.betcha.Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.SendBetRequest;
import edu.purdue.a307.betcha.Models.User;
import edu.purdue.a307.betcha.Models.Users;
import retrofit2.Response;

public class EditBetSyncService extends IntentService {

    String betID;

    public EditBetSyncService() {
        super("EditBetSyncService");
    }


    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        betID = workIntent.getStringExtra("betID");
        Users users = new Gson().fromJson(workIntent.getStringExtra("users"), Users.class);

        ArrayList<User> newUsers = new ArrayList<User>();
        for(User user: users.users) {
            try {
                Response<BetchaResponse> response = ApiHelper.getInstance(getApplicationContext()).sendBet(
                        new SendBetRequest(user.getId(), betID, SharedPrefsHelper.getSelfToken(
                                getApplicationContext()))).execute();
                if(response.code()!= 200) {
                    BToast.makeShort(getApplicationContext(), "There was an error");
                    break;
                }
                newUsers.add(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager
//                .getInstance(EditBetSyncService.this);
        Intent intent = new Intent("edu.purdue.a307.betcha.close");
        users.users = newUsers;
        intent.putExtra("users", new Gson().toJson(users));
        getApplicationContext().sendBroadcast(intent);



    }
}
