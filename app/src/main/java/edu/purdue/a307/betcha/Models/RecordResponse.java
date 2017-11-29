package edu.purdue.a307.betcha.Models;

/**
 * Created by kushagra on 11/28/17.
 */

public class RecordResponse {
    private int losses;
    private int wins;

    public RecordResponse(int losses, int wins) {
        this.losses = losses;
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
