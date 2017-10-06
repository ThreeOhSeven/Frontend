package edu.purdue.a307.betcha.Models;

import java.util.List;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class BetInformation {

    public String id;
    public String creator;
    public String maxUsers;
    public String title;
    public String description;
    public String amount;
    public String winner;
    public String locked;
//    public List<BetUsers> betUsers;


    public BetInformation() {}

    public BetInformation(String creator, String maxUsers, String title, String amount) {
        this.creator = creator;
        this.maxUsers = maxUsers;
        this.title = title;
        this.amount = amount;
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

//    public List<BetUsers> getBetUsers() {
//        return betUsers;
//    }
//
//    public void setBetUsers(List<BetUsers> betUsers) {
//        this.betUsers = betUsers;
//    }
}
