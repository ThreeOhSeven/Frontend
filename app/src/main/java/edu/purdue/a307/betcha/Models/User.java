package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 10/5/17.
 */

public class User {
    String id;
    String username;
    String email;
    String birthday;

    public User(String id, String username, String email, String birthday) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
