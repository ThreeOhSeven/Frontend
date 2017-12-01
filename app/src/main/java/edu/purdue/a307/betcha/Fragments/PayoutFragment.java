package edu.purdue.a307.betcha.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.purdue.a307.betcha.Api.ApiHelper;
import edu.purdue.a307.betcha.Helpers.BToast;
import edu.purdue.a307.betcha.Helpers.SharedPrefsHelper;
import edu.purdue.a307.betcha.Listeners.OnPageSelectedListener;
import edu.purdue.a307.betcha.Models.BetchaResponse;
import edu.purdue.a307.betcha.Models.ConnectAccountRequest;
import edu.purdue.a307.betcha.Models.PayoutRequest;
import edu.purdue.a307.betcha.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayoutFragment extends Fragment implements OnPageSelectedListener {

    @BindView(R.id.charge)
    EditText amount;


    public PayoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_payout, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.payoutButton)
    public void onPayout() {
        if(amount.getText().toString().isEmpty()) {
            BToast.makeShort(getActivity(),"Type in an amount");
            return;
        }

        ApiHelper.getInstance(getActivity()).payoutUser(
                new PayoutRequest(SharedPrefsHelper.getSelfToken(getActivity()),
                        amount.getText().toString())).enqueue(new Callback<BetchaResponse>() {
            @Override
            public void onResponse(Call<BetchaResponse> call, Response<BetchaResponse> response) {
                if(response.code() != 200) {
                    BToast.makeShort(getActivity(), "There was an error in the payout");
                    return;
                }

                BToast.makeShort(getActivity(), "Success");
            }

            @Override
            public void onFailure(Call<BetchaResponse> call, Throwable t) {
                BToast.makeShort(getActivity(), "There was a failure in the payout");

            }
        });
    }

    @Override
    public void onPageSelected() {

    }
}
