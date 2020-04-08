import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    login: function(data, callback) {
        web.post(`/login`, data, callback);
    },
};
