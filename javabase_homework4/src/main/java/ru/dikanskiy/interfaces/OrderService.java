package ru.dikanskiy.interfaces;

import ru.dikanskiy.entities.implementations.user.User;

public interface OrderService {

    String displayProducts(User user);

    long checkout(User user);

}
