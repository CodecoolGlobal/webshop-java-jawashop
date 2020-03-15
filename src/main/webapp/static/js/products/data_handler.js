import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    getAll: function() {
        return web.get('/product');
    },

    addToShoppingCart: function(id) {
        return web.post(`/cart`, new Map([["id", id]]));
    },

    getProductsByCategory: function (id) {
        return web.get(`/products-by-category?id=${id}`);
    },

    getProductsBySupplier(id) {
        return web.get(`/products-by-supplier?id=${id}`);
    },
}
