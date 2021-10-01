package ru.dikanskiy.interfaces;

import java.util.List;

public interface Cart {

    void setCartList(List<Product> products);

    void addToCart(Product products);

    void setProduct(int index, Product product);

    Product getProduct(int index) throws IndexOutOfBoundsException;

    void removeProduct(int index);

    void clearCart();

    boolean isEmpty();

    List<Product> getProducts();

}
