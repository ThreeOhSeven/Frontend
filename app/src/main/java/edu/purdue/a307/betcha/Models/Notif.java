package edu.purdue.a307.betcha.Models;

import java.util.Date;

/**
 * Created by Peter on 11/30/17.
 */

public class Notif {
    private int id;
    private int userId;
    private String title;
    private String message;
    private boolean viewed;
    private String creationTime;
    private int type;

    public Notif(int id, int userId, String title, String message, boolean viewed, String creationTime, int type) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.viewed = viewed;
        this.creationTime = creationTime;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
