import { template } from "./templates.js";

export let ui = {
    __rootNode: null,

    init() {
        ui.__rootNode = document.querySelector(".navbar");
    },

    renderCategoryDropdown: function(categories) {
        const dropdownCategoryRoot = ui.__rootNode.querySelector("#category-dropdown");
        dropdownCategoryRoot.innerHTML = categories.map(template.categoryDropdownTemplate).join("");
    },

    renderSupplierDropdown: function(suppliers) {
        const dropdownCategoryRoot = ui.__rootNode.querySelector("#supplier-dropdown");
        dropdownCategoryRoot.innerHTML = suppliers.map(template.supplierDropdownTemplate).join("");
    },

    updateCartButtonStats: function(cart) {
        const cartItemCounterNode = ui.__rootNode.querySelector("#cart-item-counter");
        cartItemCounterNode.innerHTML = cart.item_count;
    },

    addClickEventToCategoriesFilter: function(callback) {
        const categoryLinkNodes = ui.__rootNode.querySelectorAll(".category-dropdown-item");
        categoryLinkNodes.forEach(filterOption => filterOption.addEventListener("click", function() {
            const categoryId = this.dataset.id;
            callback(categoryId);
        }));
    },

    addClickEventToSuppliersFilter: function(callback) {
        const supplierLinkNodes = ui.__rootNode.querySelectorAll(".supplier-dropdown-item");
        supplierLinkNodes.forEach(filterOption => filterOption.addEventListener("click", function() {
            const supplierId = this.dataset.id;
            callback(supplierId);
        }));
    },

    addClickEventToShoppingCart: function(callback) {
        const cartButtonNode = ui.__rootNode.querySelector("#cart-button");
        cartButtonNode.addEventListener("click", function() {
            callback();
        })
    },

    addClickEventToRegistrationButton: function(clickCallback) {
        const regBtn = ui.__rootNode.querySelector("#registrationBtn");
        regBtn.addEventListener("click", clickCallback);
    },

    addClickEventToLoginButton: function(clickCallback) {
        const loginBtn = ui.__rootNode.querySelector("#loginBtn");
        loginBtn.addEventListener("click", clickCallback);
    },

    addClickEventToLogoutButton: function(clickCallback) {
        const logoutBtn = ui.__rootNode.querySelector("#logoutBtn");
        logoutBtn.addEventListener("click", clickCallback);
    },

    renderAsAuthenticated: function() {
        ui.__rootNode.querySelector("#registrationBtn").parentNode.classList.add("d-none");
        ui.__rootNode.querySelector("#loginBtn").parentNode.classList.add("d-none");
        ui.__rootNode.querySelector("#profileMenu").classList.remove("d-none");
    },

    renderAsLoggedOut: function() {
        ui.__rootNode.querySelector("#registrationBtn").parentNode.classList.remove("d-none");
        ui.__rootNode.querySelector("#loginBtn").parentNode.classList.remove("d-none");
        ui.__rootNode.querySelector("#profileMenu").classList.add("d-none");
    },

    addClickEventToOrderHistory: function(callback) {
        const menuBtn = ui.__rootNode.querySelector("#orderHistoryBtn");
        menuBtn.addEventListener("click", callback);
    },
};
