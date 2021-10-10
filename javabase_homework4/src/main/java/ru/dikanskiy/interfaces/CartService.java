package ru.dikanskiy.interfaces;

import ru.dikanskiy.entities.implementations.product.Product;

import java.util.List;

public interface CartService {

    void setCartList(List<Product> products);

    void addToCart(Product products);

    void setProduct(int index, Product product);

    Product getProduct(int index) throws IndexOutOfBoundsException;

    void removeProduct(int index);

    void clearCart();

    boolean isEmpty();

    List<Product> getProducts();

}
