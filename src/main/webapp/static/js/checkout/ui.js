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
            const addressParts = [ "Country", "City", "Zip", "Address" ];
            let filledShippingAddressesCount = 0;
            addressParts.forEach(function (addressPart) {
                if (formData[`shipping${addressPart}`] !== "") {
                    filledShippingAddressesCount++;
                }
            });
            const isShippingAddressTheSame = filledShippingAddressesCount === 0;
            if (isShippingAddressTheSame) {
                addressParts.forEach(function (addressPart) {
                    formData[`shipping${addressPart}`] = formData[`billing${addressPart}`];
                });
                callback(formData);
            } else {
                Notiflix.Report.Failure(
                    "Error",
                    "Either fill out the all shipping address details or leave it all empty if its the same " +
                            "with the billing address.",
                    "Ok!");
            }

            return false;
        };
    }
};
