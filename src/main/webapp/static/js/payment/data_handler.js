import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    addPayment: function(data, callback) {
        web.post(`/payment`, data, callback);
    },

    confirmPayment: function(data, callback) {
        web.post(`/confirm`, data, callback);
    },
};
