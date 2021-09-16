package entities.implementations;

import entities.User;
import entities.interfaces.Notification;

public class PushNotification implements Notification {

    private final String body;
    private final User user;

    public PushNotification(String body, User user) {
        this.body = body;
        this.user = user;
    }

    @Override
    public String getNotificationBody() {
        return user.getName() + " " + body;
    }

    @Override
    public User getUser() {
        return user;
    }

}
