package ru.dikanskiy.interfaces;

import ru.dikanskiy.entities.implementations.user.User;

public interface Order {

    String displayProducts(User user);

    long checkout(User user);

}
