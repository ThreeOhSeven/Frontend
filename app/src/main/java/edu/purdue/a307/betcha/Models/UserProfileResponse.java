package edu.purdue.a307.betcha.Models;

/**
 * Created by Peter on 11/27/17.
 */

public class UserProfileResponse {

    private User user;
    private Bets bets;

    public UserProfileResponse(User user, Bets bets) {
        this.user = user;
        this.bets = bets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bets getBets() {
        return bets;
    }

    public void setBets(Bets bets) {
        this.bets = bets;
    }
}
