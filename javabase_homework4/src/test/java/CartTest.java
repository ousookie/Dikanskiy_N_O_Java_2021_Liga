import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import ru.dikanskiy.entities.implementations.cart.CartImpl;
import ru.dikanskiy.entities.implementations.product.Cheese;
import ru.dikanskiy.entities.implementations.product.Milk;
import ru.dikanskiy.entities.implementations.user.Guest;
import ru.dikanskiy.entities.interfaces.Product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CartTest {

    @InjectMocks
    CartImpl cart;

    @InjectMocks
    Guest user;

    @Test
    public void addToCartTest() {
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        final Product milkProduct = new Milk("Milk", 135, "Some milk");
        cart.addToCart(milkProduct);
        Assert.assertEquals(cart.getProduct(0), milkProduct);
    }

    @Test
    public void getProductTest() {
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        final Product milkProduct = new Milk("Milk", 155, "Some milk");
        cart.addToCart(milkProduct);
        Assert.assertEquals(cart.getProduct(0), milkProduct);
    }

    @Test
    public void removeProductTest() {
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        final Product milkProduct = new Milk("Milk", 145, "Some milk");
        cart.addToCart(milkProduct);
        cart.removeProduct(0);
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
            cart.getProduct(0);
        });
    }

    @Test
    public void clearCartTest() {
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        cart.clearCart();
        Assert.assertTrue(cart.isEmpty());
    }

    @Test
    public void cartIsEmptyTest() {
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        for (int i = 0; i < 10; i++) {
            cart.addToCart(new Cheese("Cheese", i * i, "Some cheese"));
        }
        cart.clearCart();
        Assert.assertTrue(user.getCart().isEmpty());
    }

    @Test
    public void setProductTest() {
        user.setCart(cart);
        user.getCart().setCartList(new LinkedList<>());
        cart.addToCart(new Cheese("Cheese", 199, "Some cheese"));
        final Product milkProduct = new Milk("Milk", 155, "Some milk");
        cart.setProduct(0, milkProduct);
        Assert.assertEquals(cart.getProduct(0), milkProduct);
    }

    @Test
    public void getProductsTest() {
        List<Product> products = new LinkedList<>();
        products.add(new Cheese("Cheese", 199, "Some cheese"));
        user.setCart(cart);
        user.getCart().setCartList(products);
        Assert.assertEquals(products, user.getCart().getProducts());
    }

    @Test
    public void setCartListTest() {
        user.setCart(cart);
        user.getCart().setCartList(new ArrayList<>());
        Assert.assertEquals(ArrayList.class, user.getCart().getProducts().getClass());
    }

}
