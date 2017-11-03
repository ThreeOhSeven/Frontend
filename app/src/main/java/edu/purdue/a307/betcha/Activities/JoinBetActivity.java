package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Enums.JoinBetType;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.AcceptBetRequest;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.JoinBetRequest;
import edu.purdue.a307.betcha.Models.RejectBetRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinBetActivity extends BetchaActivity {

    JSONObject obj = null;
    int joinBetType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        joinBetType = getIntent().getIntExtra("type", 2);


        // Initialize Views
        TextView mTitle = (TextView) findViewById(R.id.joinTitle);
        TextView mDescription = (TextView) findViewById(R.id.joinDescription);
        TextView mAmount = (TextView) findViewById(R.id.joinAmount);
        Button mAButton = (Button) findViewById(R.id.joinSideAButton);
        Button mBButton = (Button) findViewById(R.id.joinSideBButton);
        Button rejectBtn = (Button)findViewById(R.id.rejectButton);

        if(joinBetType == 0) {
            rejectBtn.setVisibility(View.INVISIBLE);
        }


        // Get Strings from Bet Object Passed from Last Activity
        try {
            obj = new JSONObject(getIntent().getStringExtra("Obj"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            mTitle.setText(obj.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            mDescription.setText(obj.getString("description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            mAmount.setText(obj.getString("amount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            mAButton.setText(obj.getString("sideA"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            mBButton.setText(obj.getString("sideB"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Add click listener to SideA Button
        mAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = null;
                try {
                    id = obj.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                joinSideA(id);
            }
        });

        // Add click listener to SideB Button
        mBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = null;
                try {
                    id = obj.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                joinSideB(id);
            }
        });

        rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = null;
                try {
                    id = obj.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ApiHelper.getInstance(getApplicationContext()).rejectBet(
                        new RejectBetRequest(id, selfToken)).enqueue(new Callback<BetchaResponse>() {
                    @Override
                    public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                        if(response.code() != 200) {
                            BToast.makeShort(getApplicationContext(), "Cannot reject the bet (ERROR)");
                            return;
                        }
                        BToast.makeShort(getApplicationContext(), "Bet Rejected");
                        finish();
                    }

                    @Override
                    public void onFailure(Call<BetchaResponse> call, Throwable t) {
                        BToast.makeShort(getApplicationContext(), "Cannot reject the bet (FAILURE)");
                    }
                });
            }
        });

    }

    protected int getLayoutResource() { return R.layout.activity_join_bet; }


    // Function to joinSideA
    private void joinSideA(String id) {
        String selfToken = SharedPrefsHelper.getSelfToken(this);
        int side = 0;

        JoinBetRequest joinBetRequest = new JoinBetRequest(id, side, selfToken);

        if(joinBetType == 0) {
            ApiHelper.getInstance(this).joinBet(joinBetRequest).enqueue(new Callback<BetchaResponse>() {
                @Override
                public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                    if(response.code() != 200) {
                        Log.d("Like Response Code", Integer.toString(response.code()));

                        String errorMessage = "Error";

                        try {
                            JSONObject obj = new JSONObject(response.errorBody().string());

                            errorMessage = obj.getString("error");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(JoinBetActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                    } else {
                        Log.d("Like Response Status", "Successful");


                        JoinBetActivity.this.finish();
                    }
                }

                @Override
                public void onFailure(Call<BetchaResponse> call, Throwable t) {
                    Log.d("Like Update: ", "Failure");
                    Toast.makeText(JoinBetActivity.this, "Failed to POST like", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(joinBetType == 1) {
            ApiHelper.getInstance(this).acceptBet(joinBetRequest).enqueue(new Callback<BetchaResponse>() {
                @Override
                public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                    if(response.code() != 200) {
                        Log.d("Like Response Code", Integer.toString(response.code()));

                        String errorMessage = "Error";

                        try {
                            JSONObject obj = new JSONObject(response.errorBody().string());

                            errorMessage = obj.getString("error");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(JoinBetActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                    } else {
                        Log.d("Like Response Status", "Successful");


                        JoinBetActivity.this.finish();
                    }
                }

                @Override
                public void onFailure(Call<BetchaResponse> call, Throwable t) {
                    Log.d("Like Update: ", "Failure");
                    Toast.makeText(JoinBetActivity.this, "Failed to POST like", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    // Function to joinSideB
    private void joinSideB(String id) {
        String selfToken = SharedPrefsHelper.getSelfToken(this);
        int side = 1;

        JoinBetRequest joinBetRequest = new JoinBetRequest(id, side, selfToken);

        if(joinBetType == 0) {
            ApiHelper.getInstance(this).joinBet(joinBetRequest).enqueue(new Callback<BetchaResponse>() {
                @Override
                public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                    if(response.code() != 200) {
                        Log.d("Like Response Code", Integer.toString(response.code()));

                        String errorMessage = "Error";

                        try {
                            JSONObject obj = new JSONObject(response.errorBody().string());

                            errorMessage = obj.getString("error");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(JoinBetActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                    } else {
                        Log.d("Like Response Status", "Successful");


                        JoinBetActivity.this.finish();
                    }
                }

                @Override
                public void onFailure(Call<BetchaResponse> call, Throwable t) {
                    Log.d("Like Update: ", "Failure");
                    Toast.makeText(JoinBetActivity.this, "Failed to POST like", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(joinBetType == 1) {
            ApiHelper.getInstance(this).acceptBet(joinBetRequest).enqueue(new Callback<BetchaResponse>() {
                @Override
                public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                    if(response.code() != 200) {
                        Log.d("Like Response Code", Integer.toString(response.code()));

                        String errorMessage = "Error";

                        try {
                            JSONObject obj = new JSONObject(response.errorBody().string());

                            errorMessage = obj.getString("error");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(JoinBetActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                    } else {
                        Log.d("Like Response Status", "Successful");


                        JoinBetActivity.this.finish();
                    }
                }

                @Override
                public void onFailure(Call<BetchaResponse> call, Throwable t) {
                    Log.d("Like Update: ", "Failure");
                    Toast.makeText(JoinBetActivity.this, "Failed to POST like", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
