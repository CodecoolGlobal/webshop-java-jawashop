package com.codecool.shop.controller;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.ShoppingCartDaoJDBC;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.exception.UnAuthorizedException;
import com.codecool.shop.jsonbuilder.CartItemJsonBuilder;
import com.codecool.shop.jsonbuilder.CurrencyJsonBuilder;
import com.codecool.shop.jsonbuilder.ProductJsonBuilder;
import com.codecool.shop.jsonbuilder.SupplierJsonBuilder;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class ShoppingCartController extends AuthenticatedController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        super.doGet(req, resp);

        List<CartItem> cartItems = new ShoppingCartDaoJDBC().getAll();

        JsonObjectBuilder cartBuilder = calculateCartStats(cartItems);

        if (req.getParameter("list") != null) {
            cartBuilder.add(
                    "items",
                    CartItemJsonBuilder.create()
                            .addProducts(ProductJsonBuilder.create()
                                .addId()
                                .addName()
                                .addDescription()
                                .addPrice()
                                .addCurrency(CurrencyJsonBuilder.create()
                                        .addSymbol()
                                        .addDisplayName())
                                .addSupplier(SupplierJsonBuilder.create()
                                        .addName()))
                            .addQuantity()
                        .runOn(cartItems));
        }

        super.jsonify(cartBuilder.build(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, InternalServerException, UnAuthorizedException {
        super.doPost(req, resp);

        String id = super.getIdFrom(req);
        Product product = new ProductDaoJDBC().find(id);
        ShoppingCartDao shoppingCartDao = new ShoppingCartDaoJDBC();

        if(product == null) {
            throw new IllegalArgumentException("There is no Product { ID = " + id + "}");
        }

        shoppingCartDao.add(product);

        List<CartItem> cartItems = shoppingCartDao.getAll();

        JsonObjectBuilder rootObject = calculateCartStats(cartItems);
        appendCartItem(product, cartItems, rootObject);

        super.jsonify(rootObject.build(), req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, InternalServerException, UnAuthorizedException {
        super.doDelete(req, resp);

        String id = super.getIdFrom(req);
        Product product = new ProductDaoJDBC().find(id);
        ShoppingCartDao shoppingCartDao = new ShoppingCartDaoJDBC();

        if(product == null) {
            throw new IllegalArgumentException("There is no Product { ID = " + id + "}");
        }

        CartItem cartItem = shoppingCartDao.find(product);

        if (0 < cartItem.getQuantity()) {
            cartItem.changeQuantity(-1);
            shoppingCartDao.update(cartItem);
        }

        if (cartItem.getQuantity() == 0) {
            shoppingCartDao.remove(cartItem);
        }

        List<CartItem> cartItems = shoppingCartDao.getAll();

        JsonObjectBuilder rootObject = calculateCartStats(cartItems);
        appendCartItem(product, cartItems, rootObject);

        super.jsonify(rootObject.build(), req, resp);
    }

    private JsonObjectBuilder calculateCartStats(List<CartItem> cartItems) {
        float totalPrice = 0;
        int itemCount = 0;

        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getProduct().getDefaultPrice() * cartItem.getQuantity();
            itemCount += cartItem.getQuantity();
        }

        String totalValue = totalPrice + " JPY";

        JsonObjectBuilder cartBuilder = Json.createObjectBuilder()
                .add("item_count", itemCount)
                .add("total_value", totalValue);
        return cartBuilder;
    }

    private void appendCartItem(Product product, List<CartItem> cartItems, JsonObjectBuilder rootObject) {
        CartItem cartItem = findCurrent(product, cartItems);

        if (cartItem == null) {
            cartItem = new CartItem(product, 0);
        }

        JsonObject quantityObject = CartItemJsonBuilder.create()
                .addProducts(ProductJsonBuilder.create()
                    .addId())
                .addQuantity()
                .runOn(cartItem);

        rootObject.add("cart_item", quantityObject);
    }

    private CartItem findCurrent(Product product, List<CartItem> cartItems) {
        return cartItems.stream().filter(t -> t.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
    }
}
