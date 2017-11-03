package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 11/2/17.
 */

public class CompleteBetRequest {
    public String authToken;
    public String betID;
    public String winner;

    public CompleteBetRequest(String authToken, String betID, String winner) {
        this.authToken = authToken;
        this.betID = betID;
        this.winner = winner;
    }

    public CompleteBetRequest() {}

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getBetID() {
        return betID;
    }

    public void setBetID(String betID) {
        this.betID = betID;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
