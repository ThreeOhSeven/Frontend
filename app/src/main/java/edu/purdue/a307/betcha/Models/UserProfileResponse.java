package edu.purdue.a307.betcha.Models;

import java.util.List;

/**
 * Created by Peter on 11/27/17.
 */

public class UserProfileResponse {

    private User user;
    private List<Bet> bets;

    public UserProfileResponse(User user, List<Bet> bets) {
        this.user = user;
        this.bets = bets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
