package edu.purdue.a307.betcha.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.purdue.a307.betcha.Activities.PaymentActivity;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.PaymentRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment implements OnPageSelectedListener {




    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_payment, container, false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this,v);
        cardInputWidget = (CardInputWidget) v.findViewById(R.id.card_input_widget);
        return v;
    }

    CardInputWidget cardInputWidget;

    @BindView(R.id.payButton)
    Button pay;

    @BindView(R.id.charge)
    EditText charge;

    @OnClick(R.id.payButton)
    public void pay() {
        Card card = cardInputWidget.getCard();
        if (card == null) {
            BToast.makeShort(getActivity(), "Card is null");
//            BToast.makeError(PaymentActivity.this, getString(R.string.payment_reject));
            return;
        }

        if(!card.validateCard()) {
            BToast.makeShort(getActivity(), "Card is not validated");
//            BToast.makeError(PaymentActivity.this, getString(R.string.payment_reject));
            return;
        }

        if(charge.getText().toString().isEmpty()) {
            BToast.makeShort(getActivity(), "Charge empty");
//            BToast.makeError(PaymentActivity.this, getString(R.string.payment_reject));
            return;
        }

        final String stripetok = getString(R.string.stripe_token);

        Stripe stripe = new Stripe(getActivity(), getString(R.string.stripe_token));
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        Log.d("Stripe Token", token.getId());
                        // Send token to your server
                        ApiHelper.getInstance(getActivity()).chargeUser(new PaymentRequest(
                                SharedPrefsHelper.getSelfToken(getActivity()), token.getId(),
                                charge.getText().toString())).enqueue(new Callback<BetchaResponse>() {
                            @Override
                            public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                                if(response.code() != 200) {
                                    BToast.makeShort(getActivity(), "Not 200");
//                                    BToast.makeError(PaymentActivity.this, getString(R.string.payment_reject));
                                    return;
                                }
                                BToast.makeSuccess(getActivity(), getString(R.string.payment_accept));

                            }

                            @Override
                            public void onFailure(Call<BetchaResponse> call, Throwable t) {
                                BToast.makeShort(getActivity(), "Failed");
//                                BToast.makeServerError(PaymentActivity.this);
                            }
                        });
                    }
                    public void onError(Exception error) {
                        BToast.makeError(getActivity(), getString(R.string.payment_reject));
                    }
                }
        );
    }


    @Override
    public void onPageSelected() {

    }
}
