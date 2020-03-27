import { getViewRoot, exportFormInputs } from "./../ui.js";
import { template } from "./templates.js";

export let ui = {
    __rootNode: null,

    render: function() {
        ui.__rootNode = getViewRoot();
        ui.__rootNode.innerHTML = template.forPage();
        ui.__addClickListenerOnSubmitBtn(function(formData) {
            console.log(formData);
        });
    },

    __addClickListenerOnSubmitBtn: function(callback) {
        ui.__rootNode.querySelector("button").addEventListener("click", function () {
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
                callback(formData);
            } else {
                Notiflix.Report.Failure(
                    "Error",
                    "Either fill out the all shipping address details or leave it all empty if its the same " +
                            "with the billing address.",
                    "Ok!");
            }
        });
    }
};
