// Generates the needed HTML

import { template } from "./templates.js";
import { dataHandler } from "./data_handler.js";

export let ui = {
    getViewRoot: function() {
        return document.getElementById("content-container");
    },
    createIndexPage: function(response) {
        ui.createFilteredPage(response);
        ui.addCategoryEventListener();
    },
    createFilteredPage: function(response) {
        const htmlRoot = ui.getViewRoot();
        htmlRoot.innerHTML = "";
        const root = document.createElement("div");
        root.classList.add("row");
        console.log(response);
        const products = response.products;
        root.innerHTML = products.map(template.productTemplate).join("");
        htmlRoot.appendChild(root);
        ui.addToShoppingCartEventListener();
    },
    createCategoryDropdown: function(response) {
        const dropdownCategoryRoot = document.getElementById("category-dropdown");
        const categories = response.categories;
        dropdownCategoryRoot.innerHTML = categories.map(template.dropdownTemplate).join("");
        ui.attachEventListeners();
    },
    createSupplierDropdown: function(response) {
        const dropdownCategoryRoot = document.getElementById("supplier-dropdown");
        const suppliers = response.suppliers;
        dropdownCategoryRoot.innerHTML = suppliers.map(template.dropdownTemplate).join("");
    },
    getCategoryDropdown: function() {
        const categoryDropdown = document.querySelectorAll("#category-dropdown");
        return categoryDropdown[0].children;
    },

    addCategoryEventListener: function() {
        const filterOptions = document.querySelectorAll(".dropdown-item");
        filterOptions.forEach(filterOption => filterOption.addEventListener("click", function () {
            const categoryId = this.dataset.id;
            dataHandler.getProductsByCategory(categoryId, ui.createFilteredPage);
        }))
    },

    attachEventListeners : function() {
        ui.addCategoryEventListener();
        ui.addToShoppingCartEventListener();
    },

    addToShoppingCartEventListener : function() {
        const buttons = document.querySelectorAll(".add-to-cart-btn");
        buttons.forEach(button => button.addEventListener("click", function(){
            const productId = this.dataset.id;
            dataHandler.addToShoppingCart(productId, ui.updateCartButtonStats);
        }))
    },

    updateCartButtonStats: function(response) {
        const mobileNode = document.querySelector("#cart-item-total-value-mobile");
        mobileNode.innerHTML = template.forShoppingCartButton(response.message);

        const totalValueNode = document.querySelector("#cart-item-total-value");
        totalValueNode.innerHTML = response.message.cart.total_value;

        const cartItemCounterNode = document.querySelector("#cart-item-counter");
        cartItemCounterNode.innerHTML = response.message.cart.item_count;
    }
};
