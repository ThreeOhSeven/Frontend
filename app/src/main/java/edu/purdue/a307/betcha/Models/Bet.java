package edu.purdue.a307.betcha.Models;



/**
 * Created by Peter on 10/1/17.
 */

public class Bet {

    public int id;
    public int maxUsers;
    public String title;
    public String text;
    public boolean completed;


    public Bet(int id, int maxUsers, String title, String text, boolean completed) {
        this.id = id;
        this.maxUsers = maxUsers;
        this.title = title;
        this.text = text;
        this.completed = completed;
    }
}
