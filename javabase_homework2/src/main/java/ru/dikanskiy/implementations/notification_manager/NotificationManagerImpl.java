package ru.dikanskiy.implementations.notification_manager;

import ru.dikanskiy.interfaces.Notification;
import ru.dikanskiy.interfaces.NotificationManager;
import ru.dikanskiy.interfaces.User;

public class NotificationManagerImpl implements NotificationManager {

    @Override
    public void send(User user, Notification notification) {
        user.addNotification(notification);
    }

}
