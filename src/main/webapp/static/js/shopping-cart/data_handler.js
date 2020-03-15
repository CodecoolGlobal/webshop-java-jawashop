import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    getProducts: function(callback) {
        web.get('/cart?list=true', callback);
    },

    getStats: function(callback) {
        web.get('/cart', callback);
    },

    addProduct: function(id){
        web.post(`/cart`, new Map([["id", id]]), callback);
    },
};
