import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";
import { logic as homepage } from "./../index/logic.js";
import { logic as shoppingCart } from "./../shopping-cart/logic.js";

export let navbar = {
    init: async function() {

        ui.init();

        let [categoriesJson, suppliersJson, shoppingCartJson] = await Promise.all([
            dataHandler.getCategories(ui.renderCategoryDropdown),
            dataHandler.getSuppliers(ui.renderSupplierDropdown),
            dataHandler.getShoppingCartStats(ui.updateCartButtonStats),
        ]);

        navbar.__addClickEventToMenus();
    },

    __addClickEventToMenus: function() {
        ui.addClickEventToCategoriesFilter(function(categoryId) {
            homepage.filterByCategory(categoryId);
        });

        ui.addClickEventToSuppliersFilter(function(supplierId) {
            homepage.filterBySupplier(supplierId);
        });

        ui.addClickEventToShoppingCart(function() {
            shoppingCart.navigate();
        });
    }
}
