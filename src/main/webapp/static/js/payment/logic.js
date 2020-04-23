import { dataHandler } from "./data_handler.js";
import { formValidator } from "../components/formValidator.js";
import { ui } from "./ui.js";

export let logic = {
    navigate: function(order) {
        ui.render(order, logic.__submitForm);
    },

    __submitForm: function(formData) {
        let isFilledCorrectly = true;
        if (formData.type === "credit-card") {
            if (!formValidator.isValidFullName(formData.card_owner)) {
                ui.showValidationError("Invalid Card Owner value!");
                isFilledCorrectly = false;
            }
        } else if (formData.type === "paypal") {
            if (!formValidator.isValidEmail(formData.email)) {
                ui.showValidationError("Invalid Email address");
                isFilledCorrectly = false;
            }

            if (!formValidator.isValidPassword(formData.password)) {
                ui.showValidationError("Invalid Password!");
                isFilledCorrectly = false;
            }
        } else {
            ui.showValidationError("Form is invalid! Developers are notified about this problem.");
            isFilledCorrectly = false;
        }

        if (!isFilledCorrectly) {
            return;
        }
        dataHandler.addPayment(formData, function(response) {
            if (response instanceof Array) {
                const errors = response;
                errors.forEach(error => {
                    ui.showValidationError(error);
                });
            } else {
                const order = response;
                ui.renderSuccessfulPayment(order);
            }
        });
    },
};
