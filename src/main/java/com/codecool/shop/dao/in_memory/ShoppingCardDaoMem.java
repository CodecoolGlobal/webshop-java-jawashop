package com.codecool.shop.dao.in_memory;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCardDaoMem implements ShoppingCartDao {

    private List<CartItem> data = new ArrayList<>();
    private static ShoppingCardDaoMem instance = null;

    private ShoppingCardDaoMem(){}

    public static ShoppingCardDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCardDaoMem();
        }
        return instance;
    }

    public static ShoppingCartDao create() {
        return new ShoppingCardDaoMem();
    }

    @Override
    public void add(Product product) {
        CartItem foundCartItem = find(product);

        if (foundCartItem != null) {
            foundCartItem.changeQuantity(+1);
        } else {
            this.data.add(new CartItem(product, 1));
        }
    }

    @Override
    public CartItem find(Product product) {
        return data.stream().filter(t -> t.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
    }

    @Override
    public void remove(CartItem cartItem) {
        CartItem foundCartItem = find(cartItem.getProduct());

        if (foundCartItem == null) {
            throw new IllegalStateException("There is no Product in the Cart with ID " + cartItem.getProduct().getId());
        }

        foundCartItem.changeQuantity(-1);

        if (foundCartItem.getQuantity() == 0) {
            this.data.remove(foundCartItem);
        }
    }

    @Override
    public void update(CartItem cartItem) {
        CartItem foundCartItem = find(cartItem.getProduct());

        foundCartItem.setQuantity(cartItem.getQuantity());
    }

    @Override
    public List<CartItem> getAll() {
        return data;
    }
}
