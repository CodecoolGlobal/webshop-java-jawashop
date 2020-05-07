import { getViewRoot } from "../ui.js";
import { template } from "./templates.js";

export let ui = {
    __rootNode: null,

    render: function(orders) {
        ui.__rootNode = getViewRoot();
        ui.__rootNode.innerHTML = orders.map(template.forOrder).join("");
    },
};
