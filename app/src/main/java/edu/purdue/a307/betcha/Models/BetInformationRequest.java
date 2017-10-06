package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 10/6/17.
 */

public class BetInformationRequest {
    public String authToken;
    public String id;
    public String creator;
    public String maxUsers;
    public String title;
    public String description;
    public String amount;
    public String winner;
    public String locked;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(String maxUsers) {
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }
}
