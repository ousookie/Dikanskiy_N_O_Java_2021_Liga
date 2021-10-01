package ru.dikanskiy.entities.implementations.user;

import ru.dikanskiy.interfaces.Cart;

public class User {

    private long id;
    private String name;
    private String password;
    private String role;
    private Cart cart;

    public User() {

    }

    public User(long id, String name, String password, Cart cart) {
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

    public void setRole(String role) {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    public String getRole() {
        return role;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void showCart() {
        System.out.println(cart);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", cart=" + cart +
                '}';
    }

}
