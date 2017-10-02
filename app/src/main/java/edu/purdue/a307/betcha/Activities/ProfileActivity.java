package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.purdue.a307.betcha.R;

public class ProfileActivity extends BetchaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected int getLayoutResource() { return R.layout.activity_profile; }

    public void launchPasswordUpdate(View view) {
        Intent intent = new Intent(this, PasswordUpdateActivity.class);
        startActivity(intent);
    }
}
