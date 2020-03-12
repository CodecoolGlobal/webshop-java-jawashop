// Has access for buttons and adds event-listeners
// Core can access the data-handler and the ui as well

import { ui } from "./ui.js";

function init() {
    ui.createIndexPage();
}

function attachEventListeners() {
    console.log("core.attachEventListeners");
    const categoryDropdownContainer = ui.getCategoryDropdown();
    //console.log(categoryDropdownContainer);
}

/*
    attachEventListeners : function () {
        const filterOptions = document.querySelectorAll(".dropdown-item");
        filterOptions.forEach(filterOption => filterOption.addEventListener("click", function () {
            dataHandler.getProductsByCategory(this.dataset.id, ui.createFilteredPage);
        }))
    }*/

window.addEventListener('DOMContentLoaded', init);
