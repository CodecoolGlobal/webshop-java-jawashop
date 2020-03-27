import { getViewRoot } from "./../ui.js";
import NumberPicker from "./../components/number-picker.js";
import { template } from "./templates.js";

export let ui = {
    __rootNode: null,

    renderProducts: function(cart, addProductCallback, removeProductCallback) {
        ui.__rootNode = getViewRoot();
        console.log(cart)
        ui.__rootNode.innerHTML = template.forCheckoutButton(cart.item_count);
        ui.__rootNode.innerHTML += cart.items.map(template.forProduct).join("");

        if (0 < cart.item_count) {
            ui.__rootNode.innerHTML += template.forCheckoutButton(cart.item_count);
        }

        const numberPickerNodes = document.querySelectorAll(".number-picker");
        numberPickerNodes.forEach(function (numberPickerNode, i) {
            const product = cart.items[i].product;
            let numberPicker = new NumberPicker();
            numberPicker.currentValue = cart.items[i].quantity;
            numberPicker.drawInto(numberPickerNode);

            const priceNode = numberPickerNode.parentNode.parentNode.querySelector("div");
            priceNode.textContent = `${product.price.toFixed(1)} ${product.currency.code} / count`;

            const totalValueNode = numberPickerNode.parentNode.parentNode.querySelector("div.text-center:last-child");
            totalValueNode.textContent = `total: ${(numberPicker.currentValue * parseFloat(product.price)).toFixed(1)} ${product.currency.code}`;
            numberPicker.onValueChangedEvent(function () {
                totalValueNode.textContent = `total: ${(numberPicker.currentValue * product.price).toFixed(1)} ${product.currency.code}`;
            });

            numberPicker.onValueIncreasedEvent(function () {
                addProductCallback(product, function (quantity) {
                    numberPicker.setValue(quantity);
                });
            });

            numberPicker.onValueDecreasedEvent(function () {
                removeProductCallback(product, function (quantity, shouldRemoveCard) {
                    numberPicker.setValue(quantity);

                    if (shouldRemoveCard) {
                        const cardNode = numberPickerNode.closest("div.card-container");
                        cardNode.parentNode.removeChild(cardNode);
                    }
                });
            });
        });
    },

    addClickEventToCheckoutButtons: function(callback) {
        const checkoutBtns = ui.__rootNode.querySelectorAll(".checkout-btn");
        checkoutBtns.forEach(checkoutBtn => checkoutBtn.addEventListener("click", function() {
            callback();
        }));
    },
};
