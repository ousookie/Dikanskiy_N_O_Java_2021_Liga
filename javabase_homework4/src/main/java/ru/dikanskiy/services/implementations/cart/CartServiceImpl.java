package ru.dikanskiy.services.implementations.cart;

import ru.dikanskiy.entities.implementations.product.Product;
import ru.dikanskiy.interfaces.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {

    List<Product> products;

    @Override
    public void setCartList(List<Product> products) {
        this.products = products;
    }

    @Override
    public void addToCart(Product product) {
        products.add(product);
    }

    @Override
    public Product getProduct(int index) throws IndexOutOfBoundsException {
        if (products.size() == 0) throw new IndexOutOfBoundsException();
        return products.get(index);
    }

    @Override
    public void removeProduct(int index) {
        products.remove(index);
    }

    @Override
    public void setProduct(int index, Product product) {
        products.set(index, product);
    }

    @Override
    public void clearCart() {
        products.clear();
    }

    @Override
    public boolean isEmpty() {
        return products.isEmpty();
    }

    public List<Product> getProducts() {
        return products;
    }

}
