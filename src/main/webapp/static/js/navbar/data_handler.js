import { dataHandler as web } from "./../data_handler.js";
import { dataHandler as shoppingCartDAL } from "./../shopping-cart/data_handler.js";

export let dataHandler = {
    getCategories: function(callback) {
        return web.get('/category', callback);
    },

    getSuppliers: function(callback) {
        return web.get('/supplier', callback);
    },

    getShoppingCartStats: function(callback) {
        return shoppingCartDAL.getStats(callback);
    },
}
