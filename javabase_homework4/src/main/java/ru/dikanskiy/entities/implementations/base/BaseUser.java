package ru.dikanskiy.entities.implementations.base;

import ru.dikanskiy.entities.interfaces.Cart;
import ru.dikanskiy.entities.interfaces.User;

abstract public class BaseUser implements User {

    protected long id;
    protected String name;
    protected String password;
    protected String role;
    protected Cart cart;

    public BaseUser() {

    }

    public BaseUser(long id, String name, String password, Cart cart) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.cart = cart;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract void setRole(String role);

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public abstract String toString();

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void showCart() {
        System.out.println(cart);
    }

}
