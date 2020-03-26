import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    getProducts: function(callback) {
        web.get('/cart?list=true', callback);
    },

    getStats: function(callback) {
        web.get('/cart', callback);
    },

    addProduct: function(id, callback) {
        web.post(`/cart`, { "id": id }, callback);
    },

    removeProduct: function(id, callback) {
        web.delete(`/cart`, { "id": id }, callback);
    },
};
