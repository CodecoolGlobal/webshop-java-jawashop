import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";

export let logic = {
    init: function() {

        ui.init();

        dataHandler.getAllProduct(function(response) {
            ui.renderProductsPage(response);
            logic.__addClickEventToAddToCartButtons();
        });
    },

    filterByCategory: function(categoryId) {
        dataHandler.getProductsByCategory(categoryId, function(response) {
            ui.renderProductsPage(response);
            logic.__addClickEventToAddToCartButtons();
        });
    },

    filterBySupplier: function(supplierId) {
        dataHandler.getProductsBySupplier(supplierId, function(response) {
            ui.renderProductsPage(response);
            logic.__addClickEventToAddToCartButtons();
        });
    },

    __addClickEventToAddToCartButtons: function() {
        ui.addClickEventToAddToCartButtons(function(productId) {
            dataHandler.addToShoppingCart(productId, ui.updateCartStats);
        });
    },
}
