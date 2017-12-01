package edu.purdue.a307.betcha.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Fragments.PaymentFragment;
import edu.purdue.a307.betcha.Fragments.PayoutFragment;
import edu.purdue.a307.betcha.Fragments.PrivateFeedFragment;
import edu.purdue.a307.betcha.Fragments.PublicFeedFragment;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.PaymentRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment[] getFragmentsList() {
        return new Fragment[]{new PaymentFragment(), new PayoutFragment()};
    }

    @Override
    protected String[] getStringsList() {
        return new String[]{"Make Payment", "Get Payout"};
    }
}
