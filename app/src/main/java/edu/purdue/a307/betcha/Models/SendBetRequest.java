package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 10/29/17.
 */

public class SendBetRequest {

    public String id;
    public String betID;
    public String authToken;

    public SendBetRequest(String userID, String betID, String authToken) {
        this.id = userID;
        this.betID = betID;
        this.authToken = authToken;
    }

    public String getBetID() {
        return betID;
    }

    public void setBetID(String betID) {
        this.betID = betID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserID() {
        return id;
    }

    public void setUserID(String userID) {
        this.id = userID;
    }
}
