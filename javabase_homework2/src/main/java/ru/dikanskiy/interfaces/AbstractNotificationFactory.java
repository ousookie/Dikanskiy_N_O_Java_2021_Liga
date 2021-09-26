package ru.dikanskiy.interfaces;

public interface AbstractNotificationFactory {

    Notification createNotificationImpl(String text, User user);

    Notification createDecorator(String text, User user);

}
