import { getViewRoot, exportFormInputs } from "./../ui.js";
import { template } from "./templates.js";

export let ui = {
    __rootNode: null,

    render: function(submitCallback) {
        ui.__rootNode = getViewRoot();
        ui.__rootNode.innerHTML = template.forPaymentMethodSelector();
        ui.__addClickListenerToPaymentMethods(submitCallback);
    },

    showValidationError: function(message) {
        const container = ui.__rootNode.querySelector("#formErrors");
        container.innerHTML += template.forErrorMessage(message);
    },

    __addClickListenerToPaymentMethods: function(submitCallback) {
        ui.__addClickEventToCreditCardBtn(submitCallback);
        ui.__addClickEventToPayPalBtn(submitCallback);
    },

    __addClickEventToCreditCardBtn: function(submitCallback) {
        const creditCardBtn = ui.__rootNode.querySelector("#creditCardBtn");
        creditCardBtn.addEventListener("click", function() {
            ui.__rootNode.innerHTML = template.forCreditCardForm();
            ui.__addClickEventToPayPalBtn(submitCallback);
            ui.__addClickListenerOnSubmitBtn(submitCallback);
        });
    },

    __addClickEventToPayPalBtn: function(submitCallback) {
        const paypalBtn = ui.__rootNode.querySelector("#paypalBtn");
        paypalBtn.addEventListener("click", function() {
            ui.__rootNode.innerHTML = template.forPayPalForm();
            $("#inputPassword").password();
            ui.__addClickEventToCreditCardBtn(submitCallback);
            ui.__addClickListenerOnSubmitBtn(submitCallback);
        });
    },

    __addClickListenerOnSubmitBtn: function(callback) {
        ui.__rootNode.querySelector("form").onsubmit = function() {
            ui.__rootNode.querySelector("#formErrors").innerHTML = "";

            const formData = exportFormInputs(ui.__rootNode);
            // Fix for bootstrap-show-password (creates a new input)
            delete formData.null;
            console.log("formData", formData)
            callback(formData);

            return false;
        };
    },
};
