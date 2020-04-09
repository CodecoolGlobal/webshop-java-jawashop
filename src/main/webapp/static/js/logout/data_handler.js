import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    logout: function(callback) {
        web.get(`/logout`, callback);
    },
};
