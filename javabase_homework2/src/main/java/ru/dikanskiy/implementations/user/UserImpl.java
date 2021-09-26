package ru.dikanskiy.implementations.user;

import ru.dikanskiy.interfaces.Notification;
import ru.dikanskiy.interfaces.User;
import ru.dikanskiy.locale.UserLocale;

import java.util.List;

public class UserImpl implements User {

    private List<Notification> notifications;
    private long id;
    private String name;
    private String email;
    private int age;
    private UserLocale userLocale;

    public UserImpl(List<Notification> notifications, long id, String name,
                    String email, int age, UserLocale userLocale) {
        this.notifications = notifications;
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.userLocale = userLocale;
    }

    @Override
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    @Override
    public List<Notification> getNotifications() {
        return notifications;
    }

    @Override
    public void setNotifications(List<Notification> notification) {
        this.notifications = notification;
    }

    @Override
    public UserLocale getUserLocale() {
        return userLocale;
    }

    @Override
    public void setUserLocale(UserLocale userLocale) {
        this.userLocale = userLocale;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserImpl{" +
                "notifications=" + notifications +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", userLocale=" + userLocale +
                '}';
    }

}
