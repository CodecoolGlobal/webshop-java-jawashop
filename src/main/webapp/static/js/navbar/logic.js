import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";
import { logic as homepage } from "./../index/logic.js";
import { logic as shoppingCart } from "./../shopping-cart/logic.js";

export let navbar = {
    init: async function() {

        ui.init();

        let [categoriesJson, suppliersJson, shoppingCartJson] = await Promise.all([
            dataHandler.getCategories(),
            dataHandler.getSuppliers(),
            dataHandler.getShoppingCartStats(),
        ]);

        ui.renderCategoryDropdown(categoriesJson.categories);
        ui.renderSupplierDropdown(suppliersJson.suppliers);
        ui.updateCartButtonStats(shoppingCartJson.message.cart);

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
