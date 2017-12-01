package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 12/1/17.
 */

public class PayoutRequest {
    String authToken;
    String payoutAmount;

    public PayoutRequest(String authToken, String amount) {
        this.authToken = authToken;
        this.payoutAmount = amount;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAmount() {
        return payoutAmount;
    }

    public void setAmount(String amount) {
        this.payoutAmount = amount;
    }
}
