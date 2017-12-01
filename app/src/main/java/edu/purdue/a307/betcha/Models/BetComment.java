package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class BetComment {
    String id;
    String userId;
    String betId;
    String text;
    String creationTime;
    String email;
    String photoUrl;

    public BetComment(String id, String userId, String betId, String text, String creationTime) {
        this.id = id;
        this.userId = userId;
        this.betId = betId;
        this.text = text;
        this.creationTime = creationTime;
    }

    public BetComment(String id, String userId, String betId, String text, String creationTime, String email) {
        this.id = id;
        this.userId = userId;
        this.betId = betId;
        this.text = text;
        this.creationTime = creationTime;
        this.email = email;
    }

    public BetComment(String id, String userId, String betId, String text, String creationTime, String email, String photoUrl) {
        this.id = id;
        this.userId = userId;
        this.betId = betId;
        this.text = text;
        this.creationTime = creationTime;
        this.email = email;
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBetId() {
        return betId;
    }

    public void setBetId(String betId) {
        this.betId = betId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
