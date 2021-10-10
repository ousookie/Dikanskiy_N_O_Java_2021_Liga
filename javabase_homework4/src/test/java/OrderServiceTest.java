import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import ru.dikanskiy.entities.implementations.product.Product;
import ru.dikanskiy.entities.implementations.user.User;
import ru.dikanskiy.services.implementations.cart.CartServiceImpl;
import ru.dikanskiy.services.implementations.order.OrderServiceImpl;

import java.util.LinkedList;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    OrderServiceImpl order;

    @InjectMocks
    User user;

    @InjectMocks
    CartServiceImpl cart;

    @Test
    public void displayProductsTest() {
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        final Product milkProduct = new Product("Milk", 129, "Some milk");
        final Product cheeseProduct = new Product("Cheese", 57, "Some cheese");
        cart.addToCart(milkProduct);
        cart.addToCart(cheeseProduct);
        for (int i = 0; i < cart.getProducts().size(); i++) {
            Assert.assertTrue(order.displayProducts(user).contains(cart.getProduct(i).toString()));
        }
    }

    @Test
    public void checkoutTest() {
        final int totalPrice = 285;
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        cart.addToCart(new Product("Milk", 129, "Some milk"));
        cart.addToCart(new Product("Cheese", 57, "Some cheese"));
        cart.addToCart(new Product("Milk", 99, "Some milk"));
        Assert.assertEquals(totalPrice, order.checkout(user));
    }

}

