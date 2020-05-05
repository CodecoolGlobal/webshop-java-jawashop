import { template } from "./body.template.js";

export let ui = {

    init: function() {
        const container = document.querySelector("body");
        container.innerHTML += template.forNavbar();
        container.innerHTML += '<section id ="content-container" class="container"></section>';
    },
};
