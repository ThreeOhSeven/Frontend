package edu.purdue.a307.betcha.Models;

/**
 * Created by Peter on 11/13/17.
 */

public class ApiResponse {
    private boolean result;
    private String error;

    public ApiResponse(boolean result, String error) {
        this.result = result;
        this.error = error;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
