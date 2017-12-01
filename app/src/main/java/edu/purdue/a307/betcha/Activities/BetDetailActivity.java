package edu.purdue.a307.betcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import edu.purdue.a307.betcha.Models.Bet;
import edu.purdue.a307.betcha.R;

public class BetDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        JSONObject obj = null;
        try {
            obj = new JSONObject(getIntent().getStringExtra("Obj"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView id = (TextView) findViewById(R.id.Id);
        TextView maxUsers = (TextView) findViewById(R.id.maxUsers);
        TextView title = (TextView) findViewById(R.id.Title);
        TextView text = (TextView) findViewById(R.id.Text);
        TextView completed = (TextView) findViewById(R.id.Completed);


        try {
            id.setText("Bet ID: " + obj.getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            maxUsers.setText("Max Users: " + obj.getString("maxUsers"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            title.setText("Title: " + obj.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            text.setText("Text: " + obj.getString("text"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            completed.setText("Completed: " + obj.getString("completed"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected int getLayoutResource() { return R.layout.activity_bet_detail; }

}
