import { dataHandler } from "./data_handler.js";
import { formValidator } from "../components/formValidator.js";
import { navbar } from "../navbar/logic.js";
import { logic as payment } from "../payment/logic.js";
import { ui } from "./ui.js";

export let logic = {
    navigate: function() {
        ui.render(logic.__submitForm);
    },

    __submitForm: function(formData) {
        let isFilledCorrectly = true;
        if (!formValidator.isValidFullName(formData["name"])) {
            ui.showValidationError("Invalid Name!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidEmail(formData["email"])) {
            ui.showValidationError("Invalid Email address!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidPhoneNumber(formData["phoneNumber"])) {
            ui.showValidationError("Invalid phone number!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidCountry(formData["billingCountry"])) {
            ui.showValidationError("Invalid billing country!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidCity(formData["billingCity"])) {
            ui.showValidationError("Invalid billing city!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidZipCode(formData["billingZip"])) {
            ui.showValidationError("Invalid billing ZIP code!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidAddress(formData["billingAddress"])) {
            ui.showValidationError("Invalid billing address!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidCountry(formData["shippingCountry"])) {
            ui.showValidationError("Invalid shipping country!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidCity(formData["shippingCity"])) {
            ui.showValidationError("Invalid shipping city!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidZipCode(formData["shippingZip"])) {
            ui.showValidationError("Invalid shipping ZIP code!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidAddress(formData["shippingAddress"])) {
            ui.showValidationError("Invalid shipping address!");
            isFilledCorrectly = false;
        }

        if (!isFilledCorrectly) {
            return;
        }

        formData["phoneNumber"] = formData["phoneNumber"].replace(/[+ /]/g, "") + " ";
        dataHandler.addOrder(formData, function(response) {
            if (response instanceof Array) {
                const errors = response;
                errors.forEach(error => {
                    ui.showValidationError(error);
                });
            } else {
                navbar.updateShoppingCartStats();
                payment.navigate(response);
            }
        });
    },
};
