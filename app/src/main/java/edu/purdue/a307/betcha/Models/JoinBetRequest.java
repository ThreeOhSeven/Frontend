package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 10/12/17.
 */

public class JoinBetRequest {

    private String betID;
    private int side;
    private String authToken;

    public JoinBetRequest(String betID, int side, String authToken) {
        this.betID = betID;
        this.side = side;
        this.authToken = authToken;
    }

    public String getBetID() {
        return betID;
    }

    public void setBetID(String betID) {
        this.betID = betID;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
