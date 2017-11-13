package edu.purdue.a307.betcha.Models;

/**
 * Created by Peter on 11/13/17.
 */

public class UpdateIdRequest {
    private String authToken;
    private String deviceId;

    public UpdateIdRequest(String authToken, String deviceId) {
        this.authToken = authToken;
        this.deviceId = deviceId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
