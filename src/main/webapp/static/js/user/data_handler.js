import { dataHandler as web } from "./../data_handler.js";

export let dataHandler = {
    authenticate: function(data) {
        return web.postAsync(`/auth`, data);
    },
}
