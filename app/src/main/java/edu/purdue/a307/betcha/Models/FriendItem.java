package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class FriendItem {
    String status;
    User friend;

    public FriendItem(String status, User friend) {
        this.status = status;
        this.friend = friend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
