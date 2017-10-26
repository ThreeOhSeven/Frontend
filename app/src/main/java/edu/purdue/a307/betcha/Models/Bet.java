package edu.purdue.a307.betcha.Models;


import java.io.Serializable;

/**
 * Generic Bet Class
 */

public class Bet implements Serializable {

    private int id;
    private int creatorId;
    private int maxUsers;
    private String title;
    private String description;
    private double amount;
    private boolean winner;
    private boolean locked;
    private boolean complete;
    private int numLikes;
    private boolean liked;

    public Bet(int creatorId, int maxUsers, String title, String description, double amount, boolean winner, boolean locked, boolean complete, int numLikes, boolean liked) {
        this.creatorId = creatorId;
        this.maxUsers = maxUsers;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.winner = winner;
        this.locked = locked;
        this.complete = complete;
        this.numLikes = numLikes;
        this.liked = liked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
