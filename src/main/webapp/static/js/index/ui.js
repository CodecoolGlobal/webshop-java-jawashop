import { getViewRoot } from "./../ui.js";
import { ui as navbar } from "./../navbar/ui.js";
import { template } from "./templates.js";

export let ui = {
    __rootNode: null,

    init() {
        ui.__rootNode = getViewRoot();
    },

    renderProductsPage: function(products) {
        ui.__rootNode.innerHTML = "<div class='row'></div>";
        ui.__rootNode.firstChild.innerHTML = products.map(template.forProduct).join("");
    },

    addClickEventToAddToCartButtons: function(callback) {
        const buttons = ui.__rootNode.querySelectorAll(".card-text div a.btn");
        buttons.forEach(button => button.addEventListener("click", function() {
            callback(this.dataset.id);
        }));
    },

    updateCartStats: function(stats) {
        navbar.updateCartButtonStats(stats);
    }
}
