import { getViewRoot, exportFormInputs } from "./../ui.js";
import { template } from "./templates.js";

export let ui = {
    __rootNode: null,
    __addresses: null,

    render: function(addresses, submitCallback) {
        ui.__rootNode = getViewRoot();
        ui.__addresses = addresses;

        ui.__rootNode.innerHTML = template.forPage();

        if (addresses.length === 1) {
            ui.__fillBillingAddress(addresses[0]);
            ui.__fillShippingAddress(addresses[0]);
        } else if (1 < addresses.length) {
            ui.__addresses = addresses;
            ui.__renderAddresses(ui.__addresses);
        }

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
                if (formData[`shipping${addressPart}`] !== formData[`billing${addressPart}`]
                    && formData[`shipping${addressPart}`] !== "") {
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
    },

    __renderAddresses: function(addresses) {
        const containerNode = ui.__rootNode.querySelector("#addressesContainer");
        containerNode.parentNode.parentNode.classList.remove("d-none");
        containerNode.innerHTML = addresses.map(template.forAddress).join("");
        const btns = Array.from(containerNode.querySelectorAll("li span.address-btn"));
        for (let i = 0; i < btns.length; i++) {
            if (i % 2 === 0) {
                btns[i].addEventListener("click", function() {
                    ui.__fillBillingAddress(ui.__addresses[i / 2]);
                });
            } else {
                btns[i].addEventListener("click", function() {
                    ui.__fillShippingAddress(ui.__addresses[Math.floor(i / 2)]);
                });
            }
        }
    },

    __fillBillingAddress: function(address) {
        ui.__fillAddress(address, "billing");
    },

    __fillShippingAddress: function(address) {
        ui.__fillAddress(address, "shipping");
    },

    __fillAddress: function(address, prefix) {
        ui.__rootNode.querySelector(`input[name="${prefix}Country"]`).value = address.country;
        ui.__rootNode.querySelector(`input[name="${prefix}City"]`).value = address.city;
        ui.__rootNode.querySelector(`input[name="${prefix}Zip"]`).value = address.zip_code;
        ui.__rootNode.querySelector(`input[name="${prefix}Address"]`).value = address.address;
    },
};
