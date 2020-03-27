import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    getAll: function(callback) {
        return web.get('/product', callback);
    },

    getProductsByCategory: function (id, callback) {
        return web.get(`/products-by-category?id=${id}`, callback);
    },

    getProductsBySupplier(id, callback) {
        return web.get(`/products-by-supplier?id=${id}`, callback);
    },
}
