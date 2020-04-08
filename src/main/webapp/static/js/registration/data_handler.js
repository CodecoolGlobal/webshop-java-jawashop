import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    register: function(formData, callback) {
        return web.post('/register', formData, callback);
    },
};
