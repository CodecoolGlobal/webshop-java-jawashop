import { dataHandler as web } from "./../data_handler.js";
import { dataHandler as shoppingCartDAL } from "./../shopping-cart/data_handler.js";

export let dataHandler = {
    getCategories: function() {
        return web.get('/category');
    },

    getSuppliers: function() {
        return web.get('/supplier');
    },

    getShoppingCartStats: function() {
        return shoppingCartDAL.getStats();
    },
}
