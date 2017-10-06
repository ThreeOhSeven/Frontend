package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.purdue.a307.betcha.R;

//import android.app.Fragment;
//import android.support.v7.widget.CardView;

/**
 * Created by kushagra on 9/26/17.
 */

public class BetsActivity extends BetchaActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View rootView = inflater.inflate(R.layout.fragment_bets, container, false);
        //textView = rootView.findViewById(R.id.nav_bets);
        //textView.setText(getResources().getString(R.string.bets_str));
        //textView = (TextView) findViewById(R.id.nav_bets);
        FloatingActionButton fab = (FloatingActionButton) (findViewById(R.id.fab));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(BetsActivity.this, ManageActivity.class);
                startActivity(myIntent);
            }
        });
    }

    protected int getLayoutResource() { return R.layout.fragment_bets; }
}
/*{
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bets, container, false);
        textView = (TextView)(rootView.findViewById(R.id.nav_bets));
        //(rootView.setContentView(R.layout.activity_main);
        //final TextView myTextView = (TextView) findViewById(R.id.text_view_id);
        //textView.setText(getResources().getString(R.string.bets_str));//.user_greeting);
        //View tv = rootView.findViewById(R.id.nav_bets);//.text);
        //((TextView)tv).setText("Fragment #");
        //((HomeActivity) getActivity()).setActionBarTitle(R.id.nav_bets);
        FloatingActionButton fab = (FloatingActionButton) (rootView.findViewById(R.id.fab));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();

                Fragment fragment=null;
                FragmentManager fragmentManager=null;

                fragment = new MyHome();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_nav_layout, fragment)
                        .commit();
            }
        });

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.id.nav_bets);
        //getActivity().getActionBar().setTitle("Testing");
    }


}*/
