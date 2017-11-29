package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 11/29/17.
 */

public class BetCommentDeleteRequest {
    String authToken;
    String commentId;

    public BetCommentDeleteRequest(String authToken, String commentId) {
        this.authToken = authToken;
        this.commentId = commentId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
