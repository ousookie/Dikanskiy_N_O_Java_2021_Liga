package decorator.interfaces;

import entities.User;
import entities.interfaces.Notification;

public interface Decorator extends Notification {

    @Override
    String getNotificationBody();

    @Override
    User getUser();

}
