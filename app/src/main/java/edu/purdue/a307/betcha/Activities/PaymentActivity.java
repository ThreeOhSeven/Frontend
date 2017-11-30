package edu.purdue.a307.betcha.Activities;

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
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.PaymentRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends BetchaActivity {

    CardInputWidget cardInputWidget;

    @BindView(R.id.payButton)
    Button pay;

    @BindView(R.id.charge)
    EditText charge;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        cardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);

    }


    @OnClick(R.id.payButton)
    public void pay() {
        Card card = cardInputWidget.getCard();
        if (card == null) {
            BToast.makeError(PaymentActivity.this, getString(R.string.payment_reject));
            return;
        }

        if(!card.validateCard()) {
            BToast.makeError(PaymentActivity.this, getString(R.string.payment_reject));
            return;
        }

        if(charge.getText().toString().isEmpty()) {
            BToast.makeError(PaymentActivity.this, getString(R.string.payment_reject));
            return;
        }

        final String stripetok = getString(R.string.stripe_token);

        Stripe stripe = new Stripe(this, getString(R.string.stripe_token));
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        Log.d("Stripe Token", token.getId());
                        // Send token to your server
                        ApiHelper.getInstance(PaymentActivity.this).chargeUser(new PaymentRequest(
                                SharedPrefsHelper.getSelfToken(PaymentActivity.this), "tok_visa_debit",
                                charge.getText().toString())).enqueue(new Callback<BetchaResponse>() {
                            @Override
                            public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                if (response.code() != 200) {
                                    BToast.makeShort(PaymentActivity.this, "Not 200");
//                                                          BToast.makeError(PaymentActivity.this, getString(R.string.payment_reject));
                                    return;
                                }
                                BToast.makeSuccess(PaymentActivity.this, getString(R.string.payment_accept));
                            }

                            @Override
                            public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                BToast.makeShort(PaymentActivity.this, "Failed");
//                                                      BToast.makeServerError(PaymentActivity.this);
                            }
                        });
                    }

                    public void onError(Exception error) {
                        BToast.makeError(PaymentActivity.this, getString(R.string.payment_reject));
                    }
                });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_payment;
    }
}
