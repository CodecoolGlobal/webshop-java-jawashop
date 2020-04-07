import { getViewRoot, exportFormInputs } from "./../ui.js";
import { template } from "./templates.js";

export let ui = {
    __rootNode: null,

    renderRegistrationForm: function(submitCallback) {
        ui.__rootNode = getViewRoot();

        ui.__rootNode.innerHTML = template.forRegistrationForm();

        $("#inputPassword").password();

        const formNode = ui.__rootNode.querySelector("form");

        formNode.onsubmit = function() {
            ui.__rootNode.querySelector("#formErrors").innerHTML = "";
            const formData = exportFormInputs(formNode);
            // Fix for bootstrap-show-password (creates a new input)
            delete formData.null;
            submitCallback(formData);
            return false;
        };
    },

    renderValidationError: function(message) {
        const container = ui.__rootNode.querySelector("#formErrors");
        container.innerHTML += template.forErrorMessage(message);
    },

    renderSuccessfullRegistration: function() {
        ui.__rootNode.innerHTML = template.forSuccessfullRegistration();
    },
};
