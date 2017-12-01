package edu.purdue.a307.betcha.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.BankAccount;
import com.stripe.android.model.Token;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
public class PayoutFragment extends Fragment implements OnPageSelectedListener{

    @BindView(R.id.account_number)
    EditText accountNumber;

    @BindView(R.id.routing_number)
    EditText routingNumber;

    @BindView(R.id.holding_name)
    EditText holdingName;

    @BindView(R.id.amount)
    EditText amount;


    public PayoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_payout, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.sendButton)
    public void sendPayout() {
        String acctNum = accountNumber.getText().toString();
        String routNum = routingNumber.getText().toString();
        final String holdName = holdingName.getText().toString();
        final String amt = amount.getText().toString();

        if(acctNum.isEmpty() || routNum.isEmpty() || amt.isEmpty()) {
            BToast.makeShort(getActivity(), "Type everything in");
            return;
        }

        String stripeToken = getActivity().getString(R.string.stripe_token);
        Stripe stripe = new Stripe(getActivity(), stripeToken);
        BankAccount bankAccount = new BankAccount(acctNum, "US", "usd", routNum);
        bankAccount.setAccountHolderName(holdName);
        stripe.createBankAccountToken(bankAccount, new TokenCallback() {
            @Override
            public void onSuccess(Token token) {
                Log.d("Payout Token", token.getId());
                ApiHelper.getInstance(getActivity()).payoutUser(
                        new PaymentRequest(SharedPrefsHelper.getSelfToken(getActivity()),
                                token.getId(), amt, holdName)).enqueue(new Callback<BetchaResponse>() {
                    @Override
                    public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                        if(response.code() != 200) {
                            BToast.makeShort(getActivity(), "There was an error");
                            return;
                        }
                        BToast.makeShort(getActivity(), "Success");
                    }

                    @Override
                    public void onFailure(Call<BetchaResponse> call, Throwable t) {
                        BToast.makeShort(getActivity(), "There was a server failure");
                    }
                });
            }
            @Override
            public void onError(Exception error) {
                BToast.makeShort(getActivity(),"Error in getting token");
            }
        });
    }

    @Override
    public void onPageSelected() {

    }
}
