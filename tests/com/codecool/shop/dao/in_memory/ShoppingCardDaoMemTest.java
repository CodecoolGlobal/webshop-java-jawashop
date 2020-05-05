package com.codecool.shop.dao.in_memory;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCardDaoMemTest {
    ShoppingCartDao shoppingCartDao;

    @BeforeEach
    public void init(){
        shoppingCartDao = ShoppingCardDaoMem.create();
    }

    @Test
    public void findReturnProductIfExists(){
        Product product = makeProduct();
        CartItem item = new CartItem(makeUser(), product, 0);

        shoppingCartDao.add(makeCartItem());
        shoppingCartDao.add(item);
        shoppingCartDao.add(makeCartItem());

        assertSame(product, shoppingCartDao.find(product).getProduct());
    }

    @Test
    public void findReturnNullIfProductNotExists(){
        Product product = makeProduct();

        shoppingCartDao.add(makeCartItem());
        shoppingCartDao.add(makeCartItem());
        shoppingCartDao.add(makeCartItem());

        assertNull(shoppingCartDao.find(product));
    }

    @Test
    public void removeProductReturnNull(){
        Product product = makeProduct();
        CartItem item = new CartItem(makeUser(), product, 0);

        shoppingCartDao.add(makeCartItem());
        shoppingCartDao.add(item);
        shoppingCartDao.add(makeCartItem());

        CartItem cartItem = shoppingCartDao.find(product);
        shoppingCartDao.remove(cartItem);
        assertNull(shoppingCartDao.find(product));
    }

    @Test
    public void getReturnsAllProductsIfProductListNotNull(){
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(makeCartItem());
        cartItems.add(makeCartItem());
        cartItems.add(makeCartItem());

        for (CartItem cartItem: cartItems) {
            shoppingCartDao.add(cartItem);
        }

        for (int i = 0; i < shoppingCartDao.getAll().size(); i++) {
            assertSame(cartItems.get(i).getProduct(), shoppingCartDao.getAll().get(i).getProduct());
        }
    }

    private User makeUser() {
        return new User(null, null);
    }

    private CartItem makeCartItem() {
        return new CartItem(new User(null, null), makeProduct(), 0);
    }

    private Product makeProduct(){
        return new Product(UUID.randomUUID().toString(), null,0.0f, "HUF", null, makeCategory(), makeSupplier() );
    }

    private ProductCategory makeCategory(){
        return new ProductCategory(UUID.randomUUID().toString(), null, null, null);
    }

    private Supplier makeSupplier(){
        return new Supplier(UUID.randomUUID().toString(), null, null);
    }
}