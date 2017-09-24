package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class BetchaResponse {

    public boolean result;
    public String selfToken;

    public BetchaResponse() {}

    public BetchaResponse(boolean result, String selfToken) {
        this.result = result;
        this.selfToken = selfToken;
    }

    public boolean isSuccess() {
        return result;
    }

    public void setSuccess(boolean result) {
        this.result = result;
    }

    public String getAuthToken() {
        return selfToken;
    }

    public void setAuthToken(String selfToken) {
        this.selfToken = selfToken;
    }

    @Override
    public String toString() {
        if(selfToken == null) {
            return "success: " + result + ": Token: null";
        }
        else {
            return "success: " + result + ": Token: " + selfToken;
        }
    }
}
