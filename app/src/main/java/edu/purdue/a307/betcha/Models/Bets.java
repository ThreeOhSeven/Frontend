package edu.purdue.a307.betcha.Models;

import java.util.List;

/**
 * Created by Peter on 10/4/17.
 */

public class Bets {
    public List<Bet> bets;

    public Bets(List<Bet> bets) {
        this.bets = bets;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
