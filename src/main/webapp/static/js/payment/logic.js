import { dataHandler } from "./data_handler.js";
import { formValidator } from "../components/formValidator.js";
import { ui } from "./ui.js";

export let logic = {
    navigate: function() {
        ui.render(logic.__submitForm);
    },

    __submitForm: function(formData) {
        let isFilledCorrectly = true;
        if (formData.type === "credit-card") {
            /*if (!formValidator.isValidFullName(formData.card_owner)) {
                ui.showValidationError("Invalid Card Owner value!");
                isFilledCorrectly = false;
            }*/

            // server-side JSON parser can't handle Long
            formData.card_number += " ";
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

        console.log("Payment (PayPal or Credit card) form will be send to the server")

        dataHandler.addPayment(formData, function(errors) {
            errors.forEach(error => {
                ui.showValidationError(error);
            });

            if (errors.length === 0) {
                console.log("Successful operation. Redirecting to a new page...");
            }
        });
    },
};
