package ru.dikanskiy.entities.interfaces;

public interface Order {

    String displayProducts(User user);

    long checkout(User user);

}
