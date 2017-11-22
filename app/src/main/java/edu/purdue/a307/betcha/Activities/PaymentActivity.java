package edu.purdue.a307.betcha.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.R;

public class PaymentActivity extends BetchaActivity {

    CardInputWidget cardInputWidget;

    @BindView(R.id.payButton)
    Button pay;

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

        Stripe stripe = new Stripe(this, getString(R.string.stripe_token));
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                        BToast.makeSuccess(PaymentActivity.this, getString(R.string.payment_accept));
                    }
                    public void onError(Exception error) {
                        // Show localized error message
                        BToast.makeError(PaymentActivity.this, getString(R.string.payment_reject));
                    }
                }
        );
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_payment;
    }
}
