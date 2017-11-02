package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 11/1/17.
 */

public class InviteFriendsObj {
    public User friend;
    public boolean isChecked;

    public InviteFriendsObj(User friend) {
        this.friend = friend;
        this.isChecked = false;
    }
}
