import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";

export let logic = {
    init: async function() {

        ui.init();

        const json = await dataHandler.getAllProduct();
        ui.renderProductsPage(json.message);
        logic.__addClickEventToAddToCartButtons();
    },

    filterByCategory: async function(categoryId) {
        const json = await dataHandler.getProductsByCategory(categoryId);
        ui.renderProductsPage(json.message);
        logic.__addClickEventToAddToCartButtons();
    },

    filterBySupplier: async function(supplierId) {
        const json = await dataHandler.getProductsBySupplier(supplierId);
        ui.renderProductsPage(json.message);
        logic.__addClickEventToAddToCartButtons();
    },

    __addClickEventToAddToCartButtons: function() {
        ui.addClickEventToAddToCartButtons(async function(productId) {
            const json = await dataHandler.addToShoppingCart(productId);
            ui.updateCartStats(json.message);
        });
    },
}
