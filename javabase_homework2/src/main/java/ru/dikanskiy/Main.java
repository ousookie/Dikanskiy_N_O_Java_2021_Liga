package ru.dikanskiy;

import ru.dikanskiy.implementations.factory.NotificationFactoryImpl;
import ru.dikanskiy.implementations.notification_manager.NotificationManagerImpl;
import ru.dikanskiy.implementations.user.UserImpl;
import ru.dikanskiy.interfaces.Notification;
import ru.dikanskiy.interfaces.AbstractNotificationFactory;
import ru.dikanskiy.interfaces.NotificationManager;
import ru.dikanskiy.interfaces.User;
import ru.dikanskiy.locale.UserLocale;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<User> users = users();
        AbstractNotificationFactory abstractNotificationFactory = new NotificationFactoryImpl();
        createNotifications(new NotificationManagerImpl(), users, abstractNotificationFactory);
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(new UserImpl(new ArrayList<>(), 1, "Николай",
                "ru@gmail.com", 21, UserLocale.RUSSIAN));
        users.add(new UserImpl(new ArrayList<>(), 2, "Jhon",
                "en@gmail.com", 33, UserLocale.ENGLISH));
        users.add(new UserImpl(new ArrayList<>(), 3, "Stefan",
                "de@gmail.com", 67, UserLocale.GERMAN));
        users.add(new UserImpl(new ArrayList<>(), 4, "Emmanuel",
                "fr@gmail.com", 13, UserLocale.FRENCH));
        users.add(new UserImpl(new ArrayList<>(), 5, "Carlo",
                "it@gmail.com", 44, UserLocale.ITALIAN));
        users.add(new UserImpl(new ArrayList<>(), 6, "凛",
                "jp@gmail.com", 11, UserLocale.JAPANESE));
        users.add(new UserImpl(new ArrayList<>(), 7, "다솜",
                "kr@gmail.com", 45, UserLocale.KOREAN));
        users.add(new UserImpl(new ArrayList<>(), 8, "光辉",
                "ch@gmail.com", 24, UserLocale.SIMPLIFIED_CHINESE));
        return users;
    }

    private static void createNotifications(NotificationManager notificationManager, List<User> users,
                                            AbstractNotificationFactory abstractNotificationFactory) {
        for (User user : users) {
            Notification commonNotification = abstractNotificationFactory.createNotificationImpl("Здравствуйте", user);
            Notification translatedNotification = abstractNotificationFactory.createDecorator("Здравствуйте", user);
            notificationManager.send(user, commonNotification);
            notificationManager.send(user, translatedNotification);
        }
    }

}
