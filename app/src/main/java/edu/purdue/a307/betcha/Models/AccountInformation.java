package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class AccountInformation {
    String id;
    String name;
    String username;
    String email;

    public AccountInformation(String name, String userName, String email) {
        this.name = name;
        this.username = userName;
        this.email = email;
    }

    public AccountInformation() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
