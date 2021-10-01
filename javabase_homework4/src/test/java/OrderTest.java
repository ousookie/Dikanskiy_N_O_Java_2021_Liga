import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import ru.dikanskiy.entities.implementations.product.ProductImpl;
import ru.dikanskiy.entities.implementations.user.User;
import ru.dikanskiy.interfaces.Product;
import ru.dikanskiy.services.implementations.cart.CartImpl;
import ru.dikanskiy.services.implementations.order.OrderImpl;

import java.util.LinkedList;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

    @InjectMocks
    OrderImpl order;

    @InjectMocks
    User user;

    @InjectMocks
    CartImpl cart;

    @Test
    public void displayProductsTest() {
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        final Product milkProduct = new ProductImpl("Milk", 129, "Some milk");
        final Product cheeseProduct = new ProductImpl("Cheese", 57, "Some cheese");
        cart.addToCart(milkProduct);
        cart.addToCart(cheeseProduct);
        for (int i = 0; i < cart.getProducts().size(); i++) {
            Assert.assertTrue(order.displayProducts(user).contains(cart.getProduct(i).toString()));
        }
    }

    @Test
    public void checkoutTest() {
        final int totalSum = 285;
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        cart.addToCart(new ProductImpl("Milk", 129, "Some milk"));
        cart.addToCart(new ProductImpl("Cheese", 57, "Some cheese"));
        cart.addToCart(new ProductImpl("Milk", 99, "Some milk"));
        Assert.assertEquals(totalSum, order.checkout(user));
    }

}

