import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";

export let logic = {
    navigate: async function() {
        const json = await dataHandler.getProducts();
        ui.renderProducts(json.message.cart);
        logic.__addClickEventToAddToCartButtons();
    },

    __addClickEventToAddToCartButtons: function() {
        ui.addClickEventToAddToCartButtons(async function(productId) {
            const json = await dataHandler.addToShoppingCart(productId);
            ui.updateCartStats(json.message.cart);
        });
    },
}
