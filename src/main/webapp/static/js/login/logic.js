import { dataHandler } from "./data_handler.js";
import { formValidator } from "../components/formValidator.js";
import { navbar } from "../navbar/logic.js";
import { ui } from "./ui.js";

export let logic = {
    navigateLoginPage: function() {
        ui.render(logic.__submitForm);
    },

    __submitForm: function(formData) {
        let isFilledCorrectly = true;
        if (!formValidator.isValidEmail(formData["email"])) {
            ui.showValidationError("Invalid Email address!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidPassword(formData["password"])) {
            ui.showValidationError("Invalid password! It should be at least 8 characters long.");
            isFilledCorrectly = false;
        }

        if (!isFilledCorrectly) {
            return;
        }

        dataHandler.login(formData, function(errors) {
            errors.forEach(error => {
                ui.showValidationError(error);
            });

            if (errors.length === 0) {
                // change login and register buttons to logout button
            }
        });
    },
};
