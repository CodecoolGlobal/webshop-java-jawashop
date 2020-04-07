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
        const mobileNode = ui.__rootNode.querySelector("#cart-item-total-value-mobile");
        mobileNode.innerHTML = template.forShoppingCartButton(cart);

        const totalValueNode = ui.__rootNode.querySelector("#cart-item-total-value");
        totalValueNode.innerHTML = cart.total_value;

        const cartItemCounterNode = ui.__rootNode.querySelector("#cart-item-counter");
        cartItemCounterNode.innerHTML = cart.item_count;

        if (0 < cart.item_count) {
            cartItemCounterNode.classList.add("d-lg-block");
            totalValueNode.classList.add("d-lg-block");
        } else {
            cartItemCounterNode.classList.remove("d-lg-block");
            cartItemCounterNode.classList.add("d-none");

            totalValueNode.classList.remove("d-lg-block");
            totalValueNode.classList.add("d-none");
        }
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
};
