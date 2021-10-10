package ru.dikanskiy.entities.implementations.user;

import ru.dikanskiy.interfaces.CartService;

public class User {

    private long id;
    private String name;
    private String password;
    private String role;
    private CartService cartService;

    public User() {

    }

    public User(long id, String name, String password, CartService cartService) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.cartService = cartService;
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

    public CartService getCart() {
        return cartService;
    }

    public void setCart(CartService cartService) {
        this.cartService = cartService;
    }

    public void showCart() {
        System.out.println(cartService);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", cart=" + cartService +
                '}';
    }

}
