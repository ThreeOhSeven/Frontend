package edu.purdue.a307.betcha.Models;

import java.util.List;

/**
 * Created by Peter on 11/30/17.
 */

public class NotificationsResponse {
    private List<Notif> notifications;

    public NotificationsResponse(List<Notif> notifications) {
        this.notifications = notifications;
    }

    public List<Notif> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notif> notifications) {
        this.notifications = notifications;
    }
}
