import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    getProducts: async function() {
        return web.get('/cart?list=true');
    },

    getStats: function() {
        return web.get('/cart');
    },

    addProduct: function(id){
        return web.post(`/cart`, new Map([["id", id]]));
    },
};
