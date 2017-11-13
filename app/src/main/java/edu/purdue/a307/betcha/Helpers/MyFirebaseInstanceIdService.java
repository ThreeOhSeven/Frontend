package edu.purdue.a307.betcha.Helpers;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONObject;

import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Models.ApiResponse;
import edu.purdue.a307.betcha.Models.UpdateIdRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Peter on 11/12/17.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    public String authToken;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(refreshedToken);

        if(authToken == null) {
            authToken = SharedPrefsHelper.getSelfToken(this);
        }


        UpdateIdRequest updateIdRequest = new UpdateIdRequest(authToken, refreshedToken);

        ApiHelper.getInstance(MyFirebaseInstanceIdService.this).
                postDeviceId(updateIdRequest).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code() != 200) {
                    try {
                        JSONObject err = new JSONObject(response.errorBody().string());

                        Log.d("Error", "POST Error");
                        Toast.makeText(getApplicationContext(), "Error in creating bet",
                                Toast.LENGTH_SHORT).show();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("Failure", "Failure to post");
            }
        });
    }
}
