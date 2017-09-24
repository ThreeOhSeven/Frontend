package edu.purdue.a307.betcha.Activities;

import android.accounts.Account;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.util.Collections;

import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.LoginRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private SignInButton signInButton;
    Button signOut;
    private GoogleApiClient apiClient;
    private static final String GMAIL_SCOPE = "https://www.googleapis.com/auth/gmail.readonly";

    // Bundle key for account object
    private static final String KEY_ACCOUNT = "key_account";

    // Request codes
    private static final int RC_SIGN_IN = 9001;
    private static final int RC_RECOVERABLE = 9002;
    private Account mAccount;

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();
    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = (SignInButton)findViewById(R.id.sign_in_button);
        signOut = (Button)findViewById(R.id.sign_out_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
                startActivityForResult(myIntent, RC_SIGN_IN);
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        if (savedInstanceState != null) {
            mAccount = savedInstanceState.getParcelable(KEY_ACCOUNT);
        }

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN).requestScopes(
                new Scope(GMAIL_SCOPE)).requestEmail().build();

        apiClient = new GoogleApiClient.Builder(this).addApi(
                Auth.GOOGLE_SIGN_IN_API, signInOptions).build();



    }

    @Override
    public void onStart() {
        super.onStart();
        apiClient.connect();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(apiClient);

        if (opr.isDone()) {
            Log.d("TAG", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_ACCOUNT, mAccount);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RC_SIGN_IN) {
            GoogleSignInResult res = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(res);
        }
    }



    private void handleSignInResult(GoogleSignInResult res) {
        Log.d("TAG", "Result = " + res.isSuccess());
        if(res.isSuccess()) {
            GoogleSignInAccount account = res.getSignInAccount();
            new TokenTask().execute(account.getAccount());
        }
        else
            mAccount = null;
    }

    private void signOut() {
        apiClient.connect();
        Auth.GoogleSignInApi.signOut(apiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        signInButton.setVisibility(View.VISIBLE);
                        signOut.setVisibility(View.INVISIBLE);
                    }
                });
    }

    private void showErrorDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("An error has occurred");
        alertDialogBuilder.setMessage("You may have no connection or are unauthorized.");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private class TokenTask extends AsyncTask<Account, Void, String> {

        @Override
        protected String doInBackground(Account... accts) {
            try {
                GoogleAccountCredential credential =
                        GoogleAccountCredential.usingOAuth2(
                                LoginActivity.this,
                                Collections.singletonList(GMAIL_SCOPE)
                        );
                credential.setSelectedAccount(accts[0]);
                String TOKEN = credential.getToken();
                return TOKEN;
            } catch (UserRecoverableAuthIOException ure) {
                startActivityForResult(ure.getIntent(),RC_RECOVERABLE);
                return null;
            } catch (IOException e) {
                return null;
            } catch(GoogleAuthException ga) {
                return null;
            }
        }

        @Override
        protected void onCancelled() {
        }

        @Override
        protected void onPostExecute(final String TOKEN) {
            if(TOKEN == null) {
                signOut();
                return;
            }
            ApiHelper.getInstance(getApplicationContext()).login(new LoginRequest(TOKEN)).enqueue(new Callback<BetchaResponse>() {
                @Override
                public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response){
                    if (response.code() != 200) {
                        Log.d("Response Code",String.valueOf(response.code()));
                        Log.d("Response Message",String.valueOf(response.message()));
                        showErrorDialog();
                        signOut();
                    }
                    else {
                        Intent myIntent = new Intent(LoginActivity.this, HomeActivity.class);
//                        SharedPrefsHelper.getSharedPrefs(
//                                getApplicationContext()).edit().putString("authToken",response.body().getAuthToken());
                        Log.d("Body Response", response.toString());
                        Log.d("Callback Response", response.body().toString());
                        Log.d("Message", response.message());
                        if(response.body().getAuthToken() != null) {
                            Log.d("Callback Token", response.body().getAuthToken());
                        }
                        Toast.makeText(getApplicationContext(), response.body().getAuthToken(), Toast.LENGTH_LONG).show();
                        startActivity(myIntent);
                        finish();
                        signInButton.setVisibility(View.INVISIBLE);
                        signOut.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<BetchaResponse> call, Throwable t) {
                    showErrorDialog();
                    signOut();
                }
            });
            signInButton.setVisibility(View.INVISIBLE);
            signOut.setVisibility(View.VISIBLE);
        }
    }
}
