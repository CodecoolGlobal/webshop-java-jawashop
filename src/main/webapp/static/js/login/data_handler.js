import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    login: function(formData, callback) {
        web.post(`/login`, formData, callback);
    },
};
