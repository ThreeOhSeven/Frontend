package edu.purdue.a307.betcha.Activities;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.purdue.a307.betcha.Adapters.BetAdapter;
import edu.purdue.a307.betcha.Adapters.FriendAdapter;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Fragments.FriendRequestFragment;
import edu.purdue.a307.betcha.Fragments.FriendsFragment;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.BetInformations;
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
 * Created by kushagra on 10/2/17.
 */

public class FriendsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment[] getFragmentsList() {
        return new Fragment[]{new FriendsFragment(), new FriendRequestFragment()};
    }

    @Override
    protected String[] getStringsList() {
        return new String[]{"Friends", "Requests"};
    }
}
