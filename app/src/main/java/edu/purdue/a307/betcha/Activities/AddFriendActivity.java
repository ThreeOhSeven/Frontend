package edu.purdue.a307.betcha.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.purdue.a307.betcha.R;


/**
 * Created by kushagra on 10/2/17.
 */

public class AddFriendActivity extends BetchaActivity {
    private static EditText friendName, friendUserName, friendPhotoUrl;
    private static Button addFriendButton;
    private String txtFriendName, txtFriendUserName, txtFriendPhotoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View rootView = inflater.inflate(R.layout.fragment_bets, container, false);
        //textView = rootView.findViewById(R.id.nav_bets);
        //textView.setText(getResources().getString(R.string.bets_str));
        //textView = (TextView) findViewById(R.id.nav_bets);
        initViews();
        setListeners();
    }

    protected int getLayoutResource() {
        return R.layout.add_friend;
    }

    // Initiate Views
    private void initViews() {
        friendName = (EditText) findViewById(R.id.friendName);
        friendUserName = (EditText) findViewById(R.id.friendUserName);
        friendPhotoUrl = (EditText) findViewById(R.id.friendPhotoUrl);
        addFriendButton = (Button) findViewById(R.id.addFriendBtn);
    }

    // Set Listeners
    private void setListeners() {
        addFriendButton.setOnClickListener(buttonClickListener);
        //forgotPassword.setOnClickListener(this);

    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //switch (v.getId()) {
            //    case R.id.addFriendBtn:
                    //bindTextView();
                    checkValidation();
                    //Intent myIntent = new Intent(ManageActivity.this, CreateBetActivity.class);
                    //startActivity(myIntent);
            //        break;
            //}
        }
    };

    // Check Validation before login
    private void checkValidation() {
        // Get email id and password
        txtFriendName = friendName.getText().toString();
        txtFriendUserName = friendUserName.getText().toString();
        txtFriendPhotoUrl = friendPhotoUrl.getText().toString();
        // Check patter for email id
        Pattern p1 = null;
        Matcher m1=null;
        if (!txtFriendName.equals("") && txtFriendName.length() > 0) {
            Pattern.compile(Utils.regExName);
            m1 = p1.matcher(txtFriendName);
        }
        Pattern p2 = null;
        Matcher m2=null;
        if (!txtFriendUserName.equals("") && txtFriendUserName.length() > 0) {
            p2 = Pattern.compile(Utils.regExUserName);
            m2 = p2.matcher(txtFriendUserName);
        }

        Context context = getApplicationContext();
        Toast toast = new Toast(context);
        // Check for both field is empty or not
        if (txtFriendName.equals("") || txtFriendName.length() == 0) {
            new CustomToast().Show_Toast(context, toast.getView(),
                    "Enter Friend's Name.");
        } else if (txtFriendUserName.equals("") || txtFriendUserName.length() == 0) {
            //loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(context, toast.getView(),
                    "Enter Friend's User Name.");
        }
        // Check if email id is valid or not
        else if (!m1.find()) {
            new CustomToast().Show_Toast(context, toast.getView(),
                    "Friend's Name is Invalid.");
        } else if (!m2.find()) {
            new CustomToast().Show_Toast(context, toast.getView(),
                    "Friend's User Name is Invalid.");
        }
        // Else do login and do your stuff
        else {
            Toast.makeText(context, "Trying to save...", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
    /*Connection myConnection = connectionclass("sa", "80808kas;", "kushbiz", "117.247.66.51:1433");
            if(myConnection == null){
                Toast.makeText(getActivity(), "Connection to Database server failed!", Toast.LENGTH_SHORT);
            }else{
                Toast.makeText(getActivity(), "Connection to Database server succeeded!", Toast.LENGTH_SHORT);
            }*/

            /*SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, METHOD_NAME);
            PropertyInfo loginProp =new PropertyInfo();
            loginProp.name="Login";
            loginProp.setValue(getUserId);
            //loginProp.equals(getUserId);
            loginProp.type = String.class;
            request.addProperty(loginProp);//, getUserId);

            PropertyInfo pwdProp =new PropertyInfo();
            pwdProp.name = "Password";
            pwdProp.setValue(getPassword);
            //pwdProp.equals(getPassword);
            pwdProp.type = String.class;
            request.addProperty(pwdProp);//, getPassword);*/

            /*editPersons=(EditText)findViewById(R.id.edit_persons);
            editAmount=(EditText)findViewById(R.id.edit_amount);
            yourpersons=editPersons.getText().toString();
            youramount=editAmount.getText().toString();*/
//request.addProperty(propertyInfo);
            /*SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);*/

            /*try  {
                //httpTransport.call(SOAP_ACTION, envelope);
                //Object response = envelope.getResponse();
                Object response = new Submit().doInBackground(getUserId, getPassword);
                if(response!= null){
                    //string state = response.toString();
                    Log.e("Found", response.toString());
                }
                else{
                    Log.e("Obj", response.toString());
                }
            }catch (Exception exception)   {
                Log.e("Exception Encountered", exception.getMessage());
            }*/
            /*TextView tvData1;
            EditText edata;
            Button button;
            String studentNo;
            String state;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                tvData1 = (TextView)findViewById(R.id.textView1);
                edata =(EditText)findViewById(R.id.editText1);

                button=(Button)findViewById(R.id.button1);

                button.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        studentNo=edata.getText().toString();
                        new Submit().execute(studentNo);
                    }
                });
            }*/


            /*}
        }

}
    /*private static View view;

    private static EditText amount_to_bet, bet_desc;
    private static Button createBetButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;
    private static TextView textView;

    private static final String SOAP_ACTION = "http://www.kushbiz.com/isStudentLoginValid";

    private static final String METHOD_NAME = "isStudentLoginValid";// your webservice web method name

    private static final String WSDL_TARGET_NAMESPACE = "http://www.kushbiz.com/";

    private static final String SOAP_ADDRESS = "http://www.kushbiz.com/ktWSAAStudent.asmx";

    protected static final String TAG = null;
    private static String fahren;

    String getUserId;
    String getPassword;
    // Session Manager Class
    SessionManager session;

    public CreateBetActivity() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_bets, container, false);
        initViews();
        setListeners();
        session = new SessionManager(getActivity().getApplicationContext());
        Drawable drawable = amount_to_bet.getBackground(); // get current EditText drawable
        drawable.setColorFilter(Color.rgb(76,86,123), PorterDuff.Mode.SRC_ATOP); // change the drawable color

        if(Build.VERSION.SDK_INT > 16) {
            amount_to_bet.setBackground(drawable); // set the new drawable to EditText
        }else{
            amount_to_bet.setBackgroundDrawable(drawable); // use setBackgroundDrawable because setBackground required API 16
        }
        drawable = bet_desc.getBackground(); // get current EditText drawable
        drawable.setColorFilter(Color.rgb(76,86,123), PorterDuff.Mode.SRC_ATOP); // change the drawable color

        if(Build.VERSION.SDK_INT > 16) {
            bet_desc.setBackground(drawable); // set the new drawable to EditText
        }else{
            bet_desc.setBackgroundDrawable(drawable); // use setBackgroundDrawable because setBackground required API 16
        }
        return view;
    }

    public void bindTextView() {
        textView = (TextView) (view.findViewById(R.id.tvResult));
        getUserId=amount_to_bet.getText().toString();
        getPassword=bet_desc.getText().toString();
        new Submit().execute(getUserId, getPassword);
    }
    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        amount_to_bet = (EditText) view.findViewById(R.id.amount_to_bet);
        bet_desc = (EditText) view.findViewById(R.id.bet_description);
        createBetButton = (Button) view.findViewById(R.id.createBetBtn);
        //forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        //signUp = (TextView) view.findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) view
                .findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        // Setting text selector over textviews

    }

    // Set Listeners
    private void setListeners() {
        createBetButton.setOnClickListener(this);
        //forgotPassword.setOnClickListener(this);
        //signUp.setOnClickListener(this);

        // Set check listener over checkbox for showing and hiding password
        show_hide_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            bet_desc.setInputType(InputType.TYPE_CLASS_TEXT);
                            bet_desc.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            bet_desc.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            bet_desc.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createBetBtn:
                bindTextView();
                checkValidation();
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

    /*public Connection connectionclass(String user, String password, String database, String server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + server + database + ";user=" + user+ ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
            System.out.println("Database connection successful!");
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
            System.out.println(se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
            //System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
            //System.out.println(e.getMessage());
        }
        return connection;
    }*/

    /*// Check Validation before login
    private void checkValidation() {
        // Get email id and password
        getUserId = amount_to_bet.getText().toString();
        getPassword = bet_desc.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getUserId);

        // Check for both field is empty or not
        if (getUserId.equals("") || getUserId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "Enter both credentials.");

        }
        // Check if email id is valid or not
        else if (!m.find())
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your User Id is Invalid.");
            // Else do login and do your stuff
        else {
            Toast.makeText(getActivity(), "Trying to Login...", Toast.LENGTH_SHORT)
                    .show();*/
            /*Connection myConnection = connectionclass("sa", "80808kas;", "kushbiz", "117.247.66.51:1433");
            if(myConnection == null){
                Toast.makeText(getActivity(), "Connection to Database server failed!", Toast.LENGTH_SHORT);
            }else{
                Toast.makeText(getActivity(), "Connection to Database server succeeded!", Toast.LENGTH_SHORT);
            }*/

            /*SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, METHOD_NAME);
            PropertyInfo loginProp =new PropertyInfo();
            loginProp.name="Login";
            loginProp.setValue(getUserId);
            //loginProp.equals(getUserId);
            loginProp.type = String.class;
            request.addProperty(loginProp);//, getUserId);

            PropertyInfo pwdProp =new PropertyInfo();
            pwdProp.name = "Password";
            pwdProp.setValue(getPassword);
            //pwdProp.equals(getPassword);
            pwdProp.type = String.class;
            request.addProperty(pwdProp);//, getPassword);*/

            /*editPersons=(EditText)findViewById(R.id.edit_persons);
            editAmount=(EditText)findViewById(R.id.edit_amount);
            yourpersons=editPersons.getText().toString();
            youramount=editAmount.getText().toString();*/
            //request.addProperty(propertyInfo);
            /*SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);*/

            /*try  {
                //httpTransport.call(SOAP_ACTION, envelope);
                //Object response = envelope.getResponse();
                Object response = new Submit().doInBackground(getUserId, getPassword);
                if(response!= null){
                    //string state = response.toString();
                    Log.e("Found", response.toString());
                }
                else{
                    Log.e("Obj", response.toString());
                }
            }catch (Exception exception)   {
                Log.e("Exception Encountered", exception.getMessage());
            }*/
            /*TextView tvData1;
            EditText edata;
            Button button;
            String studentNo;
            String state;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                tvData1 = (TextView)findViewById(R.id.textView1);
                edata =(EditText)findViewById(R.id.editText1);

                button=(Button)findViewById(R.id.button1);

                button.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        studentNo=edata.getText().toString();
                        new Submit().execute(studentNo);
                    }
                });
            }*/


            /*}
        }

    class Submit extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        //@Override
        protected String doInBackground(String... args) {
            String state="";
            // TODO Auto-generated method stub*/
                /*SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, METHOD_NAME);
                PropertyInfo propertyInfo = new PropertyInfo();
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "eid";
                propertyInfo.setValue(studentNo);
                request.addProperty(propertyInfo);//need to be careful adding this, as it became an issue for me while I was getting continuous exception.*/

            /*SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, METHOD_NAME);
            PropertyInfo loginProp =new PropertyInfo();
            loginProp.name="Login";
            loginProp.setValue(args[0]);
            loginProp.type = String.class;
            request.addProperty(loginProp);

            PropertyInfo pwdProp =new PropertyInfo();
            pwdProp.name = "Pwd";
            pwdProp.setValue(args[1]);
            pwdProp.type = String.class;
            request.addProperty(pwdProp);

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
                    //Log.e("Found", state);
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
            if(Boolean.parseBoolean(result.toString())) {
                textView.setText("Login successful");
                //((HomeActivity)getActivity()).setMenuGroupVisibility(1,false);
                //((HomeActivity)getActivity()).setMenuGroupVisibility(2, true);
                //getActivity()
                // Session class instance
                //session = new SessionManager(getApplicationContext());
                session.createLoginSession("KT", getUserId);
                Fragment fragment = new MyHome();

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();
                //getActivity().getSupportActionBar().setTitle(title);
            }
            else
                new CustomToast().Show_Toast(getActivity(), view,
                        "Login failed.");//textView.setText("Login failed.");
        }
    }

    }*/




//}