package ru.dikanskiy.interfaces;

import ru.dikanskiy.locale.UserLocale;

import java.util.List;

public interface User {

    void addNotification(Notification notification);

    List<Notification> getNotifications();

    void setNotifications(List<Notification> notification);

    UserLocale getUserLocale();

    void setUserLocale(UserLocale userLocale);

    String getName();

    void setName(String name);

    int getAge();

    void setAge(int age);

    long getId();

    void setId(long id);

    String getEmail();

    void setEmail(String email);

}