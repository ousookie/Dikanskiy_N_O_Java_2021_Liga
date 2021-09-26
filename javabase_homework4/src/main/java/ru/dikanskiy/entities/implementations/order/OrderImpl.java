package ru.dikanskiy.entities.implementations.order;

import ru.dikanskiy.entities.interfaces.Cart;
import ru.dikanskiy.entities.interfaces.Order;
import ru.dikanskiy.entities.interfaces.Product;
import ru.dikanskiy.entities.interfaces.User;

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
