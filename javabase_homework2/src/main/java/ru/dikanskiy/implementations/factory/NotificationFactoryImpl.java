package ru.dikanskiy.implementations.factory;

import ru.dikanskiy.implementations.decorator.Decorator;
import ru.dikanskiy.implementations.notification.NotificationImpl;
import ru.dikanskiy.interfaces.Notification;
import ru.dikanskiy.interfaces.AbstractNotificationFactory;
import ru.dikanskiy.interfaces.User;

public class NotificationFactoryImpl implements AbstractNotificationFactory {

    @Override
    public Notification createNotificationImpl(String text, User user) {
        return new NotificationImpl(text, user);
    }

    @Override
    public Notification createDecorator(String text, User user) {
        return new Decorator(new NotificationImpl(text, user));
    }

}
