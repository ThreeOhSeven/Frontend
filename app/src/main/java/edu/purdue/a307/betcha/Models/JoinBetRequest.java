package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 10/12/17.
 */

public class JoinBetRequest {

    public String betID;
    public String side;
    public String authToken;

    public JoinBetRequest(String betID, String authToken, String side) {
        this.betID = betID;
        this.authToken = authToken;
        this.side = side;
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
