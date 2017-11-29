package edu.purdue.a307.betcha.Models;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class BetchaResponse implements Result {

    public boolean result;
    public String selfToken;
    public String userID;
    private String error;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getSelfToken() {
        return selfToken;
    }

    public void setSelfToken(String selfToken) {
        this.selfToken = selfToken;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    @Override
    public Status getStatus() {
        return null;
    }
}
