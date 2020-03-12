// Generates the needed HTML

import { template } from "./templates.js";
import { dataHandler } from "./data_handler.js";

export let ui = {
    getViewRoot: function() {
        return document.getElementById("content-container");
    },
    createIndexPage: function(response) {
        const htmlRoot = ui.getViewRoot();

        const root = document.createElement("div");
        root.classList.add("row");
        const products = response.products;
        root.innerHTML = products.map(template.productTemplate).join("");
        htmlRoot.appendChild(root);
    },
    createFilteredPage: function(response) {
        console.log(response)
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

    attachEventListeners : function () {
        const filterOptions = document.querySelectorAll(".dropdown-item");
        filterOptions.forEach(filterOption => filterOption.addEventListener("click", function () {
            dataHandler.getProductsByCategory(this.dataset.id, ui.createFilteredPage);
        }))
    }
};
