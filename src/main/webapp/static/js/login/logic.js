import { dataHandler } from "./data_handler.js";
import { formValidator } from "../components/formValidator.js";
import { logic as homepage } from "../index/logic.js";
import { navbar } from "../navbar/logic.js";
import { ui } from "./ui.js";
import { logic as user } from "../user/logic.js";

export let logic = {
    navigate: function() {
        ui.renderLoginForm(logic.__submitCallback);
    },

    __submitCallback: function(formData) {
        let isFilledCorrectly = true;
        if (!formValidator.isValidEmail(formData["email"])) {
            ui.renderValidationError("Invalid Email address!");
            isFilledCorrectly = false;
        }

        if (!formValidator.isValidPassword(formData["password"])) {
            ui.renderValidationError("Invalid password! It should be at least 8 characters long!");
            isFilledCorrectly = false;
        }

        if (!isFilledCorrectly) {
            return;
        }

        dataHandler.login(formData, function(errors) {
            errors.forEach(error => {
                ui.renderValidationError(error);
            });

            if (errors.length === 0) {
                navbar.updateShoppingCartStats();
                navbar.authenticated();
                homepage.getAllProducts();
            }
        });
    },
};
