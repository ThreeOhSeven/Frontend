package edu.purdue.a307.betcha.Models;

import java.util.List;

/**
 * Created by kyleohanian on 11/29/17.
 */

public class BetComments {
    List<BetComment> comments;

    public BetComments(List<BetComment> comments) {
        this.comments = comments;
    }

    public List<BetComment> getComments() {
        return comments;
    }

    public void setComments(List<BetComment> comments) {
        this.comments = comments;
    }
}
