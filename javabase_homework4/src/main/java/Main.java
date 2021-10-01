import ru.dikanskiy.entities.implementations.user.User;
import ru.dikanskiy.services.implementations.cart.CartImpl;
import ru.dikanskiy.services.implementations.order.OrderImpl;
import ru.dikanskiy.entities.implementations.product.ProductImpl;
import ru.dikanskiy.interfaces.Order;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static Order order = new OrderImpl();

    public static void main(String[] args) {
        List<User> users = users();
        fillCartsCore(users);
        fillUserCarts(users);
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
            users.add(new User(i, User.class.toString(), "12345", new CartImpl()));
        }
        return users;
    }

    private static void fillCartsCore(List<User> users) {
        for (User user : users) {
            user.getCart().setCartList(new LinkedList<>());
        }
    }

    private static void fillUserCarts(List<User> users) {
        for (User user : users) {
            if (user.getId() % 2 == 0) {
                user.getCart().addToCart(new ProductImpl("Бурёнка", 75, "Молоко"));
                user.getCart().addToCart(new ProductImpl("Докторская", 180, "Колбаса"));
            } else {
                user.getCart().addToCart(new ProductImpl("Брест-Литовск", 100, "Сыр"));
                user.getCart().addToCart(new ProductImpl("Провинция", 150, "Сосиски"));
            }
        }
    }

}
