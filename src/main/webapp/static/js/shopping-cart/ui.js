import { getViewRoot } from "./../ui.js";
import { ui as navbar } from "./../navbar/ui.js";
import NumberPicker from "./../components/number-picker.js";
import { template } from "./templates.js";

export let ui = {
    __rootNode: null,

    renderProducts: function(cart) {
        ui.__rootNode = getViewRoot();
        ui.__rootNode.innerHTML = cart.products.map(template.forProduct).join("");

        const numberPickerNodes = document.querySelectorAll(".number-picker");
        numberPickerNodes.forEach(function(numberPickerNode, i) {
            const product = cart.products[i];
            let numberPicker = new NumberPicker();
            numberPicker.drawInto(numberPickerNode);

            const priceNode = numberPickerNode.parentNode.parentNode.querySelector("div");
            priceNode.textContent = `${product.price.toFixed(1)} ${product.currency.code} / count`;

            const totalValueNode = numberPickerNode.parentNode.parentNode.querySelector("div.text-center:last-child");
            totalValueNode.textContent = `total: ${(numberPicker.currentValue * parseFloat(product.price)).toFixed(1)} ${product.currency.symbol}`;
            numberPicker.onValueChangedEvent(function() {
                totalValueNode.textContent = `total: ${(numberPicker.currentValue * product.price).toFixed(1)} ${product.currency.symbol}`;
            });

            numberPicker.onValueIncreasedEvent(function() { console.log("+1"); });
            numberPicker.onValueDecreasedEvent(function() { console.log("-1"); });
        });
    },

    addClickEventToAddToCartButtons: function(callback) {
        const buttons = ui.__rootNode.querySelectorAll(".card-text div a.btn");
        buttons.forEach(button => button.addEventListener("click", function(){
            callback(this.dataset.id);
        }));
    },
}
