package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class FriendItem {
    String name;
    String userName;
    String photoUrl;


    public FriendItem(String name, String userName, String photoUrl) {
        this.name = name;
        this.userName = userName;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
