package ru.dikanskiy.entities.interfaces;

public interface User {

    long getId();

    String getName();

    String getPassword();

    String getRole();

    void setId(long id);

    void setName(String name);

    void setPassword(String password);

    void setRole(String role);

    Cart getCart();

    void showCart();

}
