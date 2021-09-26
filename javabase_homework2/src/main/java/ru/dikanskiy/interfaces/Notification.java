package ru.dikanskiy.interfaces;

public interface Notification {

    User getUser();

    void setUser(User user);

    String getText();

    void setText(String text);

}
