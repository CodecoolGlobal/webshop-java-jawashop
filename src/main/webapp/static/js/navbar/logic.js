import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";
import { logic as login } from "./../login/logic.js";
import { logic as logout } from "./../logout/logic.js";
import { logic as orderHistory } from "./../order/logic.js";
import { logic as homepage } from "./../index/logic.js";
import { logic as registration } from "../registration/logic.js";
import { logic as shoppingCart } from "./../shopping-cart/logic.js";
import { logic as user } from "./../user/logic.js";

export let navbar = {
    init: async function() {
        ui.init();
        ui.addClickEventToLoginButton(login.navigate);
        ui.addClickEventToRegistrationButton(registration.navigate);
        ui.addClickEventToLogoutButton(logout.execute);

        if (user.isAuthenticated()) {
            navbar.authenticated();
        } else {
            navbar.loggedOut();
        }

        ui.addClickEventToShoppingCart(function() {
            shoppingCart.navigate();
        });

        ui.addClickEventToOrderHistory(function() {
            orderHistory.navigate();
        });

        await Promise.all([
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

            function() {
                if (user.isAuthenticated()) {
                    navbar.updateShoppingCartStats();
                }
            }.call(),
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
