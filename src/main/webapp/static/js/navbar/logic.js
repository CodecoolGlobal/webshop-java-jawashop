import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";
import { logic as homepage } from "./../index/logic.js";
import { logic as shoppingCart } from "./../shopping-cart/logic.js";

export let navbar = {
    init: async function() {

        ui.init();

        let [categoriesJson, suppliersJson, shoppingCartJson] = await Promise.all([
            dataHandler.getCategories(function(response) {
                ui.renderCategoryDropdown(response);
                ui.addClickEventToCategoriesFilter(function(categoryId) {
                    homepage.filterByCategory(categoryId);
                });
            }),

            dataHandler.getSuppliers(function(response) {
                ui.renderSupplierDropdown(response);
                ui.addClickEventToSuppliersFilter(function(supplierId) {
                    homepage.filterBySupplier(supplierId);
                });
            }),

            dataHandler.getShoppingCartStats(function(response) {
                ui.updateCartButtonStats(response);
                ui.addClickEventToShoppingCart(function() {
                    shoppingCart.navigate();
                });
            }),
        ]);
    },

    updateShoppingCartStats: function() {
        dataHandler.getShoppingCartStats(function(response) {
            ui.updateCartButtonStats(response);
        });
    },
};
