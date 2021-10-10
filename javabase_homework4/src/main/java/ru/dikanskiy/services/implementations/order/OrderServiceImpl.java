package ru.dikanskiy.services.implementations.order;

import ru.dikanskiy.entities.implementations.product.Product;
import ru.dikanskiy.entities.implementations.user.User;
import ru.dikanskiy.interfaces.CartService;
import ru.dikanskiy.interfaces.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public String displayProducts(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        CartService cartService = user.getCart();
        for (Product product : cartService.getProducts()) {
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
