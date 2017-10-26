package edu.purdue.a307.betcha.Models;


import java.io.Serializable;

/**
 * Created by Peter on 10/1/17.
 */

public class Bet implements Serializable {

    public int id;
    public int maxUsers;
    public String title;
    public String text;
    public boolean completed;
    public int likeCount;

    public Bet(int id, int maxUsers, String title, String text, boolean completed, int likeCount) {
        this.id = id;
        this.maxUsers = maxUsers;
        this.title = title;
        this.text = text;
        this.completed = completed;
        this.likeCount = likeCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
