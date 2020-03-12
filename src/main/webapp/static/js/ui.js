// Generates the needed HTML

import { template } from "./templates.js";

export let ui = {
    getViewRoot: function() {
        return document.getElementById("content-container");
    },
    createIndexPage: function(response) {
        const htmlRoot = ui.getViewRoot();

        const root = document.createElement("div");
        root.classList.add("row");

        for (const product of response.products) {
            root.appendChild(template.forIndexPageProduct(product));
        }

        htmlRoot.appendChild(root);
    },
    createCategoryDropdown: function(response) {
        const dropdownCategoryRoot = document.getElementById("category-dropdown");
        for (const category of response.categories) {
            dropdownCategoryRoot.appendChild(template.forDropdown(category))
        }
    },
    createSupplierDropdown: function(response) {
        const dropdownCategoryRoot = document.getElementById("supplier-dropdown");
        for (const supplier of response.suppliers) {
            dropdownCategoryRoot.appendChild(template.forDropdown(supplier))
        }
    },
    getCategoryDropdown: function() {
        const categoryDropdown = document.querySelectorAll("#category-dropdown");
        return categoryDropdown[0].children;
    }
};
