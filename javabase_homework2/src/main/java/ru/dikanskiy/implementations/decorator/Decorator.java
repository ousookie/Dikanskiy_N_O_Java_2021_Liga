package ru.dikanskiy.implementations.decorator;

import ru.dikanskiy.interfaces.Notification;
import ru.dikanskiy.interfaces.User;
import ru.dikanskiy.locale.UserLocale;

import java.util.HashMap;
import java.util.Map;

public class Decorator implements Notification {

    private static Map<UserLocale, String> translateMap = new HashMap<>();
    private Notification notification;

    static {
        for (UserLocale userLocale : UserLocale.values()) {
            translateMap.put(userLocale, userLocale.getTranslate());
        }
    }

    public Decorator(Notification notification) {
        this.notification = notification;
        translate();
    }

    @Override
    public User getUser() {
        return notification.getUser();
    }

    @Override
    public void setUser(User user) {
        notification.setUser(user);
    }

    @Override
    public String getText() {
        return notification.getText();
    }

    @Override
    public void setText(String text) {
        notification.setText(text);
    }

    @Override
    public String toString() {
        return "Decorator{" +
                "notification=" + notification +
                '}';
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public static Map<UserLocale, String> getTranslateMap() {
        return translateMap;
    }

    public static void setTranslateMap(Map<UserLocale, String> translateMap) {
        Decorator.translateMap = translateMap;
    }

    private void translate() {
        notification.setText(translateMap.get(getUser().getUserLocale()));
    }

}
