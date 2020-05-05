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
    public void add(CartItem item) {
        this.data.add(item);
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

        this.data.remove(foundCartItem);
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
