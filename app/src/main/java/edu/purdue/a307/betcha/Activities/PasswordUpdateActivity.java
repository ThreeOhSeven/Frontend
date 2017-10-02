package edu.purdue.a307.betcha.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.purdue.a307.betcha.R;

/**
 * Created by Peter on 10/2/17.
 */

public class PasswordUpdateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
    }

    public void updatePassword(View view) {
        // TODO - Get password from text edit
        // TODO - Send password to server
        // TODO - Send user back to profile
    }
}
