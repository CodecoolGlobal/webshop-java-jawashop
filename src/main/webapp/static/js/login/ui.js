import { getViewRoot, exportFormInputs } from "./../ui.js";
import { template } from "./templates.js";

export let ui = {
    __rootNode: null,

    render: function(submitCallback) {
        ui.__rootNode = getViewRoot();
        ui.__rootNode.innerHTML = template.forPage();
        ui.__addClickListenerOnSubmitBtn(submitCallback);
    },

    showValidationError: function(message) {
        const container = ui.__rootNode.querySelector("#formErrors");
        container.innerHTML += template.forErrorMessage(message);
    },

    __addClickListenerOnSubmitBtn: function(callback) {
        ui.__rootNode.querySelector("form").onsubmit = function() {
            ui.__rootNode.querySelector("#formErrors").innerHTML = "";
            const formData = exportFormInputs(ui.__rootNode);
            callback(formData);
            return false;
        };
    }
};
