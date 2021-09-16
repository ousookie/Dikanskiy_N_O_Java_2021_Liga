package factory.implementations;

import entities.User;
import entities.implementations.PhoneNotification;
import entities.implementations.PushNotification;
import entities.interfaces.Notification;
import factory.interfaces.Factory;

public class FactoryImpl implements Factory {

    @Override
    public Notification getNotification(Class<? extends Notification> tClass, String body, User user) {
        if (tClass == PushNotification.class) {
            return new PushNotification(body, user);
        } else return new PhoneNotification(body, user);
    }

}
