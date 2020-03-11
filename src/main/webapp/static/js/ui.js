// Generates the needed HTML

import { template } from "./templates.js";

export let ui = {
    getViewRoot: function() {
        return document.getElementById("content-container");
    },
    createIndexPage: function(response) {
        const htmlRoot = ui.getViewRoot();

        const root = document.createElement("div");
        root.classList.add("row");

        for (const product of response.products) {
            root.appendChild(template.forIndexPageProduct(product));
        }

        htmlRoot.appendChild(root);
    },
}
