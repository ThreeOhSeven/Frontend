package edu.purdue.a307.betcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.purdue.a307.betcha.R;

public class NewsFeedActivity extends BetchaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected int getLayoutResource() { return R.layout.activity_news_feed; }
}