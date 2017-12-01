package edu.purdue.a307.betcha.Models;

import java.util.Date;

/**
 * Created by Peter on 11/30/17.
 */

public class Notif {
    private int userId;
    private String title;
    private String message;
    private boolean viewed;
    private Date creationTime;

    public Notif(int userId, String title, String message, boolean viewed, Date creationTime) {
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.viewed = viewed;
        this.creationTime = creationTime;
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

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
