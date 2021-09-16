package factory.interfaces;

import entities.User;
import entities.interfaces.Notification;

public interface Factory {

    Notification getNotification(Class<? extends Notification> tClass, String body, User user);

}
