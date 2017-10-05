package edu.purdue.a307.betcha.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.purdue.a307.betcha.R;


/**
 * Created by kushagra on 10/2/17.
 */

public class FriendsActivity extends BetchaActivity {
    Button buttons[] = new Button[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListeners();

    }

    protected int getLayoutResource() {
        return R.layout.friends_activity;
    }

    // Set Listeners
    private void setListeners() {
        //myProfileButton.setOnClickListener(this);
        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id",
                    "edu.purdue.a307.betcha");
            switch (i) {
                case 0:
                    buttons[i] = (Button) (findViewById(R.id.addFriendBtn));
                    break;
                case 1:
                    buttons[i] = (Button) (findViewById(R.id.manageFriendsBtn));
                    break;
                case 2:
                    buttons[i] = (Button) (findViewById(R.id.removeFriendBtn));
                    break;
            }
            buttons[i].setOnClickListener(buttonClickListener);
        }
        //myScoreButton.setOnClickListener(this);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addFriendBtn:
                    Intent myIntent = new Intent(FriendsActivity.this, AddFriendActivity.class);
                    startActivity(myIntent);
                    break;
                case R.id.manageFriendsBtn:

                    break;
                case R.id.removeFriendBtn:

                    break;
            }
        }
    };
}
            /*case R.id.mySMSBtn:
                fragment = new MySMS();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();
                break;
            case R.id.logoutBtn:
                session.logoutUser();
                ((MainActivity)getActivity()).setMenuGroupVisibility(1,true);
                ((MainActivity)getActivity()).setMenuGroupVisibility(2,false);
                fragment = new Login();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();
                break;*/
            /*default:
                break;*/
            /*case R.id.forgot_password:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ForgotPassword_Fragment(),
                                Utils.ForgotPassword_Fragment).commit();
                break;
            case R.id.createAccount:

                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new SignUp_Fragment(),
                                Utils.SignUp_Fragment).commit();
                break;*/
        /*}

    }
}

    /*private static View view;

    private static Button myProfileButton;
    private static Button myScoreButton;
    private static Button createBetButton;
    private static Button logoutButton;

    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;
    private static TextView textView;


    protected static final String TAG = null;
    //private static String fahren;
    Button buttons[] = new Button[6];

    String getUserId;//="16150";
    //String getPassword;
    //SessionManager session;

    public ManageActivity() {

    }*/

    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myhome, container, false);

        initViews();
        setListeners();*/

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        /*session = new SessionManager(getActivity().getApplicationContext());
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // login
        String name = user.get(SessionManager.KEY_NAME);

        // email
        getUserId = user.get(SessionManager.KEY_EMAIL);*/

        //((HomeActivity)getActivity()).setActionBarTitle("My Home");
        /*bindTextView();
        return view;
    }

    public void bindTextView() {
        textView = (TextView) (view.findViewById(R.id.tvStudentName));
        //getUserId=login.getText().toString();
        //getPassword=password.getText().toString();
        new MyHome.Submit().execute(getUserId);//, getPassword);
    }*/
    // Initiate Views
    /*private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

//        login = (EditText) view.findViewById(R.id.login_userid);
//        password = (EditText) view.findViewById(R.id.login_password);
        myProfileButton = (Button) view.findViewById(R.id.myProfileBtn);
        createBetButton = (Button) view.findViewById(R.id.createBetBtn);
        //forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        //signUp = (TextView) view.findViewById(R.id.createAccount);
        //show_hide_password = (CheckBox) view.findViewById(R.id.show_hide_password);


        // Setting text selector over textviews

    }

    // Set Listeners
    private void setListeners() {
        //myProfileButton.setOnClickListener(this);
        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id",
                    "edu.purdue.a307.betcha");
            switch(i){
                case 0:
                    buttons[i] = (Button) (view.findViewById(R.id.myProfileBtn));
                    break;
                case 1:
                    buttons[i] = (Button) (view.findViewById(R.id.myScoreBtn));
                    break;
                case 2:
                    buttons[i] = (Button) (view.findViewById(R.id.createBetBtn));
                    break;
                case 3:
                    buttons[i] = (Button) (view.findViewById(R.id.manageBetsBtn));
                    break;
                case 4:
                    buttons[i] = (Button) (view.findViewById(R.id.manageFriendsBtn));
                    break;
                case 5:
                    buttons[i] = (Button) (view.findViewById(R.id.logoutBtn));
                    break;
            }
            buttons[i].setOnClickListener(this);
        }
        //myScoreButton.setOnClickListener(this);
    }*/

    /*@Override
    public void onClick(View v) {
        Fragment fragment=null;
        FragmentManager fragmentManager=null;
        switch (v.getId()) {
            case R.id.createBetBtn:
                fragment = new CreateBets();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_nav_layout, fragment)
                        .commit();
                break;
            case R.id.manageFriendsBtn:
                fragment = new ManageFriend();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_nav_layout, fragment)
                        .commit();
                break;*/
            /*case R.id.mySMSBtn:
                fragment = new MySMS();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();
                break;
            case R.id.logoutBtn:
                session.logoutUser();
                ((MainActivity)getActivity()).setMenuGroupVisibility(1,true);
                ((MainActivity)getActivity()).setMenuGroupVisibility(2,false);
                fragment = new Login();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();
                break;*/
            /*default:
                break;*/
            /*case R.id.forgot_password:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ForgotPassword_Fragment(),
                                Utils.ForgotPassword_Fragment).commit();
                break;
            case R.id.createAccount:

                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new SignUp_Fragment(),
                                Utils.SignUp_Fragment).commit();
                break;*/
        /*}

    }*/

    /*class Submit extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        //@Override
        protected String doInBackground(String... args) {
            String state="";
            // TODO Auto-generated method stub

            /*SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, METHOD_NAME);
            PropertyInfo loginProp =new PropertyInfo();
            loginProp.name="Login";
            loginProp.setValue(args[0]);
            loginProp.type = String.class;
            request.addProperty(loginProp);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.debug = true;
            try {

                httpTransport.call(SOAP_ACTION, envelope);

                Log.e("RequestDump", httpTransport.requestDump.toString());
                Log.e("ResponseDump", httpTransport.responseDump.toString());

                SoapObject result = (SoapObject) envelope.bodyIn;
                if (result != null) {
                    state = result.getProperty(0).toString();
                    Log.e("Found", state);
                } else {
                    Log.e("Obj", result.toString());
                    //Toast.makeText(getActivity(), result.toString(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception exception) {
                Log.e("Exception", exception.toString());
                //Toast.makeText(getActivity(), exception.toString(), Toast.LENGTH_SHORT).show();
            }*/
            /*return state;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //System.out.println("result="+result);
            //tvData1.setText(result);
            //tvResult.
            if(result.toString().length()>0)
                textView.setText(" "+result.toString()+"\n\n");
            else
                textView.setText("Session expired.");
        }
    }
}*/
