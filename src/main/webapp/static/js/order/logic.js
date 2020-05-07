import { dataHandler } from "./data_handler.js";
import { ui } from "./ui.js";

export let logic = {
    navigate: function() {
        dataHandler.getOrders(function(orders) {
            ui.render(orders);
        });
    },
};
