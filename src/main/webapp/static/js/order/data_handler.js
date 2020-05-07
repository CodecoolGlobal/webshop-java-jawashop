import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    getOrders: function(callback) {
        web.get(`/order`, callback);
    },
};
