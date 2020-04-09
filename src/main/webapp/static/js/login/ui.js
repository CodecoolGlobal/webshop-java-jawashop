import { getViewRoot, exportFormInputs } from "./../ui.js";
import { template } from "./templates.js";

export let ui = {
    __rootNode: null,

    renderLoginForm: function(submitCallback) {
        ui.__rootNode = getViewRoot();
        ui.__rootNode.innerHTML = template.forLoginForm();
        $("#inputPassword").password();
        ui.__addClickListenerOnSubmitBtn(submitCallback);
    },

    renderValidationError: function(message) {
        const container = ui.__rootNode.querySelector("#formErrors");
        container.innerHTML += template.forErrorMessage(message);
    },

    __addClickListenerOnSubmitBtn: function(callback) {
        const formNode = ui.__rootNode.querySelector("form");
        formNode.onsubmit =  function() {
            ui.__rootNode.querySelector("#formErrors").innerHTML = "";
            const formData = exportFormInputs(formNode);
            // Fix for bootstrap-show-password (creates a new input)
            delete formData.null;
            callback(formData);
            return false;
        };
    }
};
