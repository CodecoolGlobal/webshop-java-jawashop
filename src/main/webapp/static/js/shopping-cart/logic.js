import { dataHandler } from "./data_handler.js";
import { ui as navbar } from "./../navbar/ui.js";
import { ui } from "./ui.js";
import {logic as checkout} from "../checkout/logic.js";

export let logic = {

    navigate: function () {
        dataHandler.getProducts(function (response) {
            ui.renderProducts(response, logic.__addProductCallback, logic.__removeProductCallback);
            ui.addClickEventToCheckoutButtons(function() {
                checkout.navigate();
            });
        });
    },

    __addProductCallback: function (product, uiCallback) {
        dataHandler.addProduct(product.id, function(cart) {
            navbar.updateCartButtonStats(cart);
            uiCallback(cart.cart_item.quantity);
        });
    },

    __removeProductCallback: function (product, uiCallback) {
        dataHandler.removeProduct(product.id, function(cart) {
            navbar.updateCartButtonStats(cart);
            if (cart.cart_item.quantity === 0) {
                const shouldRemoveCard = true;
                uiCallback(cart.cart_item.quantity, shouldRemoveCard);
            } else {
                const shouldRemoveCard = false;
                uiCallback(cart.cart_item.quantity, shouldRemoveCard);
            }
        });
    },
};
