// Has access for buttons and adds event-listeners
// Core can access the data-handler and the ui as well

import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";

function init() {
    dataHandler.getIndexPage(ui.createIndexPage);
    dataHandler.getCategories(ui.createCategoryDropdown);
    dataHandler.getSuppliers(ui.createSupplierDropdown);
}

init();
