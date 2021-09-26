package ru.dikanskiy.implementations.notification;

import ru.dikanskiy.interfaces.Notification;
import ru.dikanskiy.interfaces.User;

public class NotificationImpl implements Notification {

    private String text;
    private User user;

    public NotificationImpl(String text, User user) {
        this.text = text;
        this.user = user;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "NotificationImpl{" +
                "text='" + text + '\'' +
                '}';
    }

}
