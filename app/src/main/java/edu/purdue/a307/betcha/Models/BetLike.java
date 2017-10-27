package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class BetLike {
    private int like;
    private int betId;

    public BetLike(int like, int betId) {
        this.like = like;
        this.betId = betId;
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
}
