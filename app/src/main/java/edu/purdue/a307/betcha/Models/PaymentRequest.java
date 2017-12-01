package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 11/26/17.
 */

public class PaymentRequest {
    String authToken;
    String stripeToken;
    String chargeAmount;
    String name;

    public PaymentRequest(String authToken, String stripeToken, String chargeAmount) {
        this.authToken = authToken;
        this.stripeToken = stripeToken;
        this.chargeAmount = chargeAmount;
    }

    public PaymentRequest(String authToken, String stripeToken, String chargeAmount, String name) {
        this.authToken = authToken;
        this.stripeToken = stripeToken;
        this.chargeAmount = chargeAmount;
        this.name = name;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getStripeToken() {
        return stripeToken;
    }

    public void setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
