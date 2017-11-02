package edu.purdue.a307.betcha.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.BindView;
import edu.purdue.a307.betcha.R;

public class JoinBetActivity extends BetchaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_bet);

        TextView mTitle = (TextView) findViewById(R.id.joinTitle);
        TextView mDescription = (TextView) findViewById(R.id.joinDescription);
        TextView mAmount = (TextView) findViewById(R.id.joinAmount);
        Button mAButton = (Button) findViewById(R.id.joinSideAButton);
        Button mBButton = (Button) findViewById(R.id.joinSideBButton);

        JSONObject obj = null;
        try {
            obj = new JSONObject(getIntent().getStringExtra("Obj"));

            Log.d("Object After", obj.toString());
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
    }

    protected int getLayoutResource() { return R.layout.activity_join_bet; }
}
