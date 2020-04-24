import { getViewRoot, exportFormInputs } from "../ui.js";
import { template } from "./templates.js";

export let ui = {
    __rootNode: null,

    render: function(order, submitCallback) {
        ui.__rootNode = getViewRoot();
        ui.__rootNode.innerHTML = template.forPaymentMethodSelector(order);
        ui.__addClickListenerToPaymentMethods(order, submitCallback);
    },

    showValidationError: function(message) {
        const container = ui.__rootNode.querySelector("#formErrors");
        container.innerHTML += template.forErrorMessage(message);
    },

    renderSuccessfulPayment: function(order) {
        ui.__rootNode.innerHTML = template.forSuccessfulPayment(order);
        const productListContainer = ui.__rootNode.querySelector("#ordered-products");
        productListContainer.innerHTML = order.products.map(template.forProduct).join("");
    },

    __addClickListenerToPaymentMethods: function(order, submitCallback) {
        ui.__addClickEventToCreditCardBtn(order, submitCallback);
        ui.__addClickEventToPayPalBtn(order, submitCallback);
    },

    __addClickEventToCreditCardBtn: function(order, submitCallback) {
        const creditCardBtn = ui.__rootNode.querySelector("#creditCardBtn");
        creditCardBtn.addEventListener("click", function() {
            ui.__rootNode.innerHTML = template.forCreditCardForm(order);
            ui.__addClickEventToPayPalBtn(order, submitCallback);
            ui.__addClickListenerOnSubmitBtn(submitCallback);
        });
    },

    __addClickEventToPayPalBtn: function(order, submitCallback) {
        const paypalBtn = ui.__rootNode.querySelector("#paypalBtn");
        paypalBtn.addEventListener("click", function() {
            ui.__rootNode.innerHTML = template.forPayPalForm(order);
            $("#inputPassword").password();
            ui.__addClickEventToCreditCardBtn(order, submitCallback);
            ui.__addClickListenerOnSubmitBtn(submitCallback);
        });
    },

    __addClickListenerOnSubmitBtn: function(callback) {
        ui.__rootNode.querySelector("form").onsubmit = function() {
            ui.__rootNode.querySelector("#formErrors").innerHTML = "";

            const formData = exportFormInputs(ui.__rootNode);
            // Fix for bootstrap-show-password (creates a new input)
            delete formData.null;
            callback(formData);

            return false;
        };
    },
};
