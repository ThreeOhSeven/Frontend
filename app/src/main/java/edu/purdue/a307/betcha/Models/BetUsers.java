package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 9/26/17.
 */

public class BetUsers {
    public String betId;
    public String userId;

    public BetUsers(String betId, String userId) {
        this.betId = betId;
        this.userId = userId;
    }

    public String getBetId() {
        return betId;
    }

    public void setBetId(String betId) {
        this.betId = betId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
