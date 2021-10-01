package ru.dikanskiy.services.implementations.order;

import ru.dikanskiy.entities.implementations.user.User;
import ru.dikanskiy.interfaces.Cart;
import ru.dikanskiy.interfaces.Order;
import ru.dikanskiy.interfaces.Product;

public class OrderImpl implements Order {

    @Override
    public String displayProducts(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        Cart cart = user.getCart();
        for (Product product : cart.getProducts()) {
            stringBuilder.append(product);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public long checkout(User user) {
        return user.getCart().getProducts().stream().mapToLong(Product::getPrice).sum();
    }

}
