package edu.purdue.a307.betcha.Models;

import edu.purdue.a307.betcha.Helpers.BDate;

/**
 * Created by kyleohanian on 12/1/17.
 */

public class ConnectAccountRequest {
    String authToken;
    String stripeToken;
    String payoutAmount;

    String firstName;
    String lastName;
    BDate dateOfBirth;
    String ssnLast4;
    String address;
    String city;
    String state;
    String postalCode;

    public ConnectAccountRequest() {}

    public ConnectAccountRequest(String authToken, String stripeToken, String payoutAmount,
                                 String firstName, String lastName, BDate dateOfBirth, String ssnLast4,
                                 String address, String city, String state, String postalCode) {
        this.authToken = authToken;
        this.stripeToken = stripeToken;
        this.payoutAmount = payoutAmount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.ssnLast4 = ssnLast4;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
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

    public String getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(String payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(BDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSsnLast4() {
        return ssnLast4;
    }

    public void setSsnLast4(String ssnLast4) {
        this.ssnLast4 = ssnLast4;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
