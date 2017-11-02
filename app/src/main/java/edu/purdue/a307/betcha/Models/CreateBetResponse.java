package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 11/1/17.
 */

public class CreateBetResponse {
    public String result;
    public String id;

    public CreateBetResponse(String result, String id) {
        this.result = result;
        this.id = id;
    }

    public CreateBetResponse() {}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
