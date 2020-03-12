// Has access for buttons and adds event-listeners
// Core can access the data-handler and the ui as well

import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";

function init() {
    dataHandler.getIndexPage(ui.createIndexPage);
    dataHandler.getCategories(ui.createCategoryDropdown);
    dataHandler.getSuppliers(ui.createSupplierDropdown);
    attachEventListeners();
}

function attachEventListeners() {
    const categoryDropdownContainer = ui.getCategoryDropdown();
    console.log(categoryDropdownContainer);
    // categoryDropdownContainer.forEach(categoryButtonContainer => function () {
    //     console.log(1);
    //     console.log(categoryButtonContainer.firstChild)
    // });
    //
    // categoryDropdownContainer.forEach(categoryButtonContainer => categoryButtonContainer.firstChild.addEventListener("click", function () {
    //     const id = this.dataset.id;
    //     console.log(id);
    //     dataHandler.getProductsByCategory(id, ui.createIndexPage);
    // }))
}

init();
