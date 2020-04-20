import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    addOrder: function(data, callback) {
        web.post(`/order`, data, callback);
    },
};
