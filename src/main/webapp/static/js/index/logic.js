import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";

export let logic = {
    init: function() {

        ui.init();

        dataHandler.getAllProduct(ui.renderProductsPage)
        logic.__addClickEventToAddToCartButtons();
    },

    filterByCategory: function(categoryId) {
        dataHandler.getProductsByCategory(categoryId, ui.renderProductsPage);
        logic.__addClickEventToAddToCartButtons();
    },

    filterBySupplier: async function(supplierId) {
        dataHandler.getProductsBySupplier(supplierId, ui.renderProductsPage);
        logic.__addClickEventToAddToCartButtons();
    },

    __addClickEventToAddToCartButtons: function() {
        ui.addClickEventToAddToCartButtons(async function(productId) {
            dataHandler.addToShoppingCart(productId, ui.updateCartStats);
        });
    },
}
