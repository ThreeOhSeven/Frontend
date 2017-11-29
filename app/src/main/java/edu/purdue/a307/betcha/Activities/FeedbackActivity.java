package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

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
import edu.purdue.a307.betcha.Models.FeedbackRequest;
import edu.purdue.a307.betcha.Models.FriendItem;
import edu.purdue.a307.betcha.Models.SendBetRequest;
import edu.purdue.a307.betcha.Models.User;
import edu.purdue.a307.betcha.Models.Users;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends BetchaActivity {

    @BindView(R.id.feedback)
    EditText feedback;
    @BindView(R.id.sendFeedbackBtn)
    Button sendFeedbackButton;



    String selfToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selfToken = SharedPrefsHelper.getSelfToken(this);

        ButterKnife.bind(this);

        // TODO - Change to be the bet passed through the intent
        feedback.setText("Feedback Text");

    }

    protected int getLayoutResource() { return R.layout.activity_feedback; }

    @OnClick(R.id.sendFeedbackBtn)
    public void sendFb() {
        final FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setText(feedback.getText().toString());
        feedbackRequest.setAuthToken(selfToken);

        // Send Feedback
        ApiHelper.getInstance(getApplicationContext()).
                sendFeedback(feedbackRequest).enqueue(new Callback<BetchaResponse>() {
            @Override
            public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                if (response.code() != 200) {
                    Log.d("Response Code",String.valueOf(response.code()));
                    Log.d("Response Message",String.valueOf(response.message()));
                    BToast.makeError(FeedbackActivity.this, getString(R.string.feedback_error));
                    return;
                }
                else {
                    Log.d("Feedback Sending", String.valueOf(response.message()));
                    BToast.makeSuccess(FeedbackActivity.this, getString(R.string.feedback_success));
//                    finish();
                }
            }

            @Override
            public void onFailure(Call<BetchaResponse> call, Throwable t) {
                BToast.makeServerError(FeedbackActivity.this);
            }
        });
    }


}
