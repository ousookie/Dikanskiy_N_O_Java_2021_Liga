import decorator.implementations.DecoratorImpl;
import entities.User;
import entities.implementations.PhoneNotification;
import entities.implementations.PushNotification;
import entities.interfaces.Notification;
import factory.interfaces.Factory;
import factory.implementations.FactoryImpl;
import locale.UserLocale;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Factory factory = new FactoryImpl();
        List<User> users = new ArrayList<>();

        users.add(new User(0, "Jhon", "en@gmail.com", null, UserLocale.ENGLISH));
        users.add(new User(1, "Stefan", "de@gmail.com", null, UserLocale.GERMAN));
        users.add(new User(2, "Алёша", "ru@gmail.com", null, UserLocale.RUSSIAN));
        users.add(new User(3, "Carlo", "it@gmail.com", null, UserLocale.ITALIAN));
        users.add(new User(4, "凛", "jp@gmail.com", null, UserLocale.JAPANESE));
        users.add(new User(5, "光辉", "ch@gmail.com", null, UserLocale.SIMPLIFIED_CHINESE));
        users.add(new User(6, "다솜", "kr@gmail.com", null, UserLocale.KOREAN));
        users.add(new User(7, "Emmanuel", "fr@gmail.com", null, UserLocale.FRENCH));

        for (User user : users) {
            System.out.println(user);
        }

        List<Notification> notifications = createNotifications(factory, users);

        System.out.println();

        for (Notification notification : notifications) {
            System.out.println(notification.getNotificationBody() + " " + notification.getClass().getSimpleName());
        }

        System.out.println();

        List<DecoratorImpl> decorators = new ArrayList<>();

        for (Notification notification : notifications) {
            decorators.add(new DecoratorImpl(notification));
        }

        for (DecoratorImpl decoratorImpl : decorators) {
            System.out.println(decoratorImpl.getNotificationBody());
        }

    }

    public static List<Notification> createNotifications(Factory factory, List<User> users) {
        List<Notification> notifications = new ArrayList<>();
        for (User user : users) {
            StringBuilder stringBuilder = new StringBuilder();
            if (user.getId() % 2 == 0) {
                notifications.add(factory.getNotification(PushNotification.class,
                        stringBuilder.append("Привет").toString(), user));
            } else {
                notifications.add(factory.getNotification(PhoneNotification.class,
                        stringBuilder.append("Привет").toString(), user));
            }
        }
        return notifications;
    }

}