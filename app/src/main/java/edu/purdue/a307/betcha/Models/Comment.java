package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 11/8/17.
 */

public class Comment {
    User user;
    String comment;
    String time;

    public Comment(User user, String comment, String time) {
        this.user = user;
        this.comment = comment;
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
