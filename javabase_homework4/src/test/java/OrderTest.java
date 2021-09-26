import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import ru.dikanskiy.entities.implementations.cart.CartImpl;
import ru.dikanskiy.entities.implementations.order.OrderImpl;
import ru.dikanskiy.entities.implementations.product.Cheese;
import ru.dikanskiy.entities.implementations.product.Milk;
import ru.dikanskiy.entities.implementations.user.Guest;

import java.util.LinkedList;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

    @InjectMocks
    OrderImpl order;

    @InjectMocks
    Guest user;

    @InjectMocks
    CartImpl cart;

    @Test
    public void checkoutTest() {
        final int checkoutValue = 285;
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        cart.addToCart(new Milk("Milk", 129, "Some milk"));
        cart.addToCart(new Cheese("Cheese", 57, "Some cheese"));
        cart.addToCart(new Milk("Milk", 99, "Some milk"));
        Assert.assertEquals(checkoutValue, order.checkout(user));
    }

    @Test
    public void displayProductsTest() {
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        final Milk milkProduct = new Milk("Milk", 129, "Some milk");
        final Cheese cheeseProduct = new Cheese("Cheese", 57, "Some cheese");
        cart.addToCart(milkProduct);
        cart.addToCart(cheeseProduct);
        for (int i = 0; i < cart.getProducts().size(); i++) {
            Assert.assertTrue(order.displayProducts(user).contains(cart.getProduct(i).toString()));
        }
    }

}
