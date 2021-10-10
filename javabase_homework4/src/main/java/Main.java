import ru.dikanskiy.entities.implementations.user.User;
import ru.dikanskiy.services.implementations.cart.CartServiceImpl;
import ru.dikanskiy.services.implementations.order.OrderServiceImpl;
import ru.dikanskiy.entities.implementations.product.Product;
import ru.dikanskiy.interfaces.OrderService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static OrderService orderService = new OrderServiceImpl();

    public static void main(String[] args) {
        List<User> users = users();
        fillCartsCore(users);
        fillUserCarts(users);
        for (User user : users) {
            System.out.println(user);
            System.out.println(orderService.displayProducts(user));
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Total: " + orderService.checkout(user) + "\n");
        }

    }

    private static List<User> users() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(i, User.class.toString(), "12345", new CartServiceImpl()));
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
                user.getCart().addToCart(new Product("Бурёнка", 90, "Молоко"));
                user.getCart().addToCart(new Product("Докторская", 180, "Колбаса"));
            } else {
                user.getCart().addToCart(new Product("Брест-Литовск", 100, "Сыр"));
                user.getCart().addToCart(new Product("Провинция", 150, "Сосиски"));
            }
        }
    }

}
