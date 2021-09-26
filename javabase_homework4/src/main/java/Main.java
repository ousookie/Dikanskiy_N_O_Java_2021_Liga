import ru.dikanskiy.entities.implementations.cart.CartImpl;
import ru.dikanskiy.entities.implementations.order.OrderImpl;
import ru.dikanskiy.entities.implementations.product.Cheese;
import ru.dikanskiy.entities.implementations.product.Milk;
import ru.dikanskiy.entities.implementations.user.Admin;
import ru.dikanskiy.entities.implementations.user.Guest;
import ru.dikanskiy.entities.interfaces.Order;
import ru.dikanskiy.entities.interfaces.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static Order order = new OrderImpl();

    public static void main(String[] args) {
        List<User> users = users();
        fillCarts(users);
        for (User user : users) {
            System.out.println(user);
            System.out.println(order.displayProducts(user));
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Total: " + order.checkout(user) + "\n");
        }
    }

    private static List<User> users() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                users.add(new Guest(i, Guest.class.toString(), "12345", new CartImpl()));
            } else {
                users.add(new Admin(i, Admin.class.toString(), "12345", new CartImpl()));
            }
        }
        return users;
    }

    private static void fillCarts(List<User> users) {
        for (User user : users) {
            user.getCart().setCartList(new LinkedList<>());
            user.getCart().addToCart(new Milk("Бурёнка", 75, "Молоко"));
            user.getCart().addToCart(new Milk("Весёлый Молочник", 100, "Молоко"));
            user.getCart().addToCart(new Milk("Пролетарское", 140, "Молоко"));
            user.getCart().addToCart(new Milk("Большая кружка", 50, "Молоко"));
            user.getCart().addToCart(new Cheese("Брест-Литовск", 80, "Сыр"));
            user.getCart().addToCart(new Cheese("Крестьянский", 175, "Сыр"));
        }
    }

}
