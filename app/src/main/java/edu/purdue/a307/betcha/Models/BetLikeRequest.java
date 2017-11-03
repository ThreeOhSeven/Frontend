package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class BetLikeRequest {
    private int like;
    private int betId;
    private String authToken;

    public BetLikeRequest(int like, int betId, String authToken) {
        this.like = like;
        this.betId = betId;
        this.authToken = authToken;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
