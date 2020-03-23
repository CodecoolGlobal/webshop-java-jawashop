import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    getAll: function(callback) {
        return web.get('/product', callback);
    },

    addToShoppingCart: function(id, callback) {
        return web.post(`/cart`, new Map([["id", id]]), callback);
    },

    getProductsByCategory: function (id, callback) {
        return web.get(`/products-by-category?id=${id}`, callback);
    },

    getProductsBySupplier(id, callback) {
        return web.get(`/products-by-supplier?id=${id}`, callback);
    },
}
