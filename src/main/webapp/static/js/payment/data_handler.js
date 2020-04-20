import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    addPayment: function(data, callback) {
        console.log("POST: /payment", data)
        web.post(`/payment`, data, callback);
    },
};
