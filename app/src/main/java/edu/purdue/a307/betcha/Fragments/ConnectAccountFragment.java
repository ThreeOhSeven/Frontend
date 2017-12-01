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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.BDate;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.ConnectAccountRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectAccountFragment extends Fragment implements OnPageSelectedListener{

    @BindView(R.id.account_number)
    EditText accountNumber;

    @BindView(R.id.routing_number)
    EditText routingNumber;

    @BindView(R.id.firstName)
    EditText firstName;

    @BindView(R.id.lastName)
    EditText lastName;

    @BindView(R.id.ssn)
    EditText ssn;

    @BindView(R.id.address)
    EditText address;

    @BindView(R.id.city)
    EditText city;

    @BindView(R.id.state)
    EditText state;

    @BindView(R.id.postalCode)
    EditText postalCode;

    public int month = 0;
    public int day = 0;
    public int year = 0;

    public ConnectAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_connect_account, container, false);
        ButterKnife.bind(this, v);
        accountNumber.setText("000123456789");
        routingNumber.setText("110000000");
        firstName.setText("Kyle");
        lastName.setText("Ohan");
        month = 1;
        day = 1;
        year = 1999;
        ssn.setText("1234");
        address.setText("120 Third Street");
        city.setText("West Lafayette");
        state.setText("Indiana");
        postalCode.setText("47906");
        return v;
    }

    @OnClick(R.id.dateOfBirth)
    public void onDateOfBirth() {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setParentFragment(this);
        fragment.show(getFragmentManager(), "datePicker");
    }

    @OnClick(R.id.sendButton)
    public void sendPayout() {
        String acctNum = accountNumber.getText().toString();
        String routNum = routingNumber.getText().toString();
        final String first = firstName.getText().toString();
        final String last = lastName.getText().toString();
        final String ssnNum = ssn.getText().toString();
        final String addr = address.getText().toString();
        final String cit = city.getText().toString();
        final String st = state.getText().toString();
        final String pc = postalCode.getText().toString();

        if(acctNum.isEmpty() || routNum.isEmpty() ||
                first.isEmpty() || last.isEmpty() || ssnNum.isEmpty() ||
                addr.isEmpty() || cit.isEmpty() || st.isEmpty() ||
                pc.isEmpty()) {
            BToast.makeShort(getActivity(), "Type everything in");
            return;
        }

        String stripeToken = getActivity().getString(R.string.stripe_token);
        Stripe stripe = new Stripe(getActivity(), stripeToken);
        BankAccount bankAccount = new BankAccount(acctNum, "US", "usd", routNum);
        stripe.createBankAccountToken(bankAccount, new TokenCallback() {
            @Override
            public void onSuccess(Token token) {
                ConnectAccountRequest connectAccountRequest = new ConnectAccountRequest();
                connectAccountRequest.setAuthToken(SharedPrefsHelper.getSelfToken(getActivity()));
                connectAccountRequest.setStripeToken(token.getId());
                connectAccountRequest.setFirstName(first);
                connectAccountRequest.setLastName(last);
                connectAccountRequest.setDateOfBirth(new BDate(String.valueOf(month),
                        String.valueOf(day), String.valueOf(year)));
                connectAccountRequest.setSsnLast4(ssnNum);
                connectAccountRequest.setAddress(addr);
                connectAccountRequest.setCity(cit);
                connectAccountRequest.setState(st);
                connectAccountRequest.setPostalCode(pc);
                Log.d("Payout Token", token.getId());
                ApiHelper.getInstance(getActivity()).connectAccount(
                        connectAccountRequest).enqueue(new Callback<BetchaResponse>() {
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
