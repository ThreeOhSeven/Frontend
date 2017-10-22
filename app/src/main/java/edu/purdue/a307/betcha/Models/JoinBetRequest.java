package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 10/12/17.
 */

public class JoinBetRequest {

    public String betID;
    public String authToken;

    public JoinBetRequest(String betID, String authToken) {
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
}
