// Generates the needed HTML

import { template } from "./templates.js";
import { dataHandler } from "./data_handler.js";

export let ui = {
    getViewRoot: function() {
        return document.getElementById("content-container");
    },
    createIndexPage: function() {
        const htmlRoot = ui.getViewRoot();

        const root = document.createElement("div");
        root.classList.add("row");
        // foo(bar) === foo(a => bar(a))
        dataHandler.getIndexPage(response => {
            const products = response.products;
            root.innerHTML = products.map(template.productTemplate).join("");
            dataHandler.getCategories(response => {
                ui.createCategoryDropdown(response);
                dataHandler.getSuppliers(response => {
                    ui.createSupplierDropdown(response);
                    ui.attachEventListeners();
                });
            })
        })
        htmlRoot.appendChild(root);
    },
    attachEventListeners: () => { console.log('Event listeners attached') },
    createFilteredPage: function(response) {
        console.log(response)
    },
    createCategoryDropdown: function(response) {
        console.log("createCategoryDropdown")
        const dropdownCategoryRoot = document.getElementById("category-dropdown");
        const categories = response.categories;
        dropdownCategoryRoot.innerHTML = categories.map(template.dropdownTemplate).join("");
        console.log("createCategoryDropdown END")
    },
    createSupplierDropdown: function(response) {
        console.log("createSupplierDropdown")
        const dropdownCategoryRoot = document.getElementById("supplier-dropdown");
        const suppliers = response.suppliers;
        dropdownCategoryRoot.innerHTML = suppliers.map(template.dropdownTemplate).join("");
        console.log("createSupplierDropdown END")
    },
    getCategoryDropdown: function() {
        const categoryDropdown = document.querySelectorAll("#category-dropdown");
        return categoryDropdown[0].children;
    },
};
