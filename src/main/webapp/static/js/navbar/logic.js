import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";
import { logic as login } from "./../login/logic.js";
import { logic as logout } from "./../logout/logic.js";
import { logic as homepage } from "./../index/logic.js";
import { logic as registration } from "../registration/logic.js";
import { logic as shoppingCart } from "./../shopping-cart/logic.js";

export let navbar = {
    init: async function() {

        ui.init();
        ui.addClickEventToLoginButton(login.navigate);
        ui.addClickEventToRegistrationButton(registration.navigate);
        ui.addClickEventToLogoutButton(logout.execute);

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

    authenticated: function() {
        ui.renderAsAuthenticated();
    },

    loggedOut: function() {
        ui.renderAsLoggedOut();
    },
};
