package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
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

        shoppingCartDao.add(makeProduct());
        shoppingCartDao.add(product);
        shoppingCartDao.add(makeProduct());

        assertSame(product, shoppingCartDao.find(product).getProduct());
    }

    @Test
    public void findReturnNullIfProductNotExists(){
        Product product = makeProduct();

        shoppingCartDao.add(makeProduct());
        shoppingCartDao.add(makeProduct());
        shoppingCartDao.add(makeProduct());

        assertNull(shoppingCartDao.find(product));
    }

    @Test
    public void removeProductReturnNull(){
        Product product = makeProduct();

        shoppingCartDao.add(makeProduct());
        shoppingCartDao.add(product);
        shoppingCartDao.add(makeProduct());

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
            shoppingCartDao.add(cartItem.getProduct());
        }

        for (int i = 0; i < shoppingCartDao.getAll().size(); i++) {
            assertSame(cartItems.get(i).getProduct(), shoppingCartDao.getAll().get(i).getProduct());
        }
    }

    private CartItem makeCartItem() {
        return new CartItem(makeProduct(), 0);
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