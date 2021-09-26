package ru.dikanskiy.entities.implementations.user;

import ru.dikanskiy.entities.implementations.base.BaseUser;
import ru.dikanskiy.entities.interfaces.Cart;

public class Guest extends BaseUser {

    private static final String USER_ROLE;

    static {
        USER_ROLE = "GUEST";
    }

    public Guest() {
        super();
    }

    public Guest(long id, String name, String password, Cart cart) {
        super(id, name, password, cart);
        setRole(USER_ROLE);
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", cart=" + cart +
                '}';
    }

}
