import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";

export let logic = {
    navigate: async function() {
        dataHandler.getProducts(function(response) {
            ui.renderProducts(response);
            logic.__addClickEventToAddToCartButtons();
        });
    },

    __addClickEventToAddToCartButtons: function() {
        ui.addClickEventToAddToCartButtons(async function(productId) {
            dataHandler.addToShoppingCart(productId, ui.updateCartStats);
        });
    },
}
