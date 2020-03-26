import { template } from "./templates.js"

export default class NumberPicker {
    minimumValue = 0;
    maximumValue = 99;
    step = 1;
    currentValue = 0;
    __valueChangedCallback = null;
    __valueIncreasedCallback = null;
    __valueDecreasedCallback = null;
    __decreaseButton;
    __numberDisplayer;
    __increaseButton;

    drawInto(container) {
        container.innerHTML = template.forNumberPicker();

        this.__decreaseButton = container.querySelector("button");
        this.__decreaseButton.addEventListener("click", event => { this.__decreaseFunction(); });

        this.__numberDisplayer = container.querySelector("input");
        this.__numberDisplayer.setAttribute("value", this.currentValue);

        this.__increaseButton = container.querySelectorAll("button").item(1);
        this.__increaseButton.addEventListener("click", event => { this.__increaseFunction(); });

        this.__checkButtonsActivity();
    }

    onValueChangedEvent(callback) {
        this.__valueChangedCallback = callback;
    }

    onValueIncreasedEvent(callback) {
        this.__valueIncreasedCallback = callback;
    }

    onValueDecreasedEvent(callback) {
        this.__valueDecreasedCallback = callback;
    }

    __decreaseFunction() {
        if (this.minimumValue <= this.currentValue - this.step) {
            this.currentValue -= this.step;
            this.__numberDisplayer.setAttribute("value", this.currentValue);
            this.__valueChangedCallback();
            this.__valueDecreasedCallback();
        }

        this.__checkButtonsActivity();
    }

    __increaseFunction() {
        if (this.currentValue + this.step <= this.maximumValue) {
            this.currentValue = this.currentValue + this.step;
            this.__numberDisplayer.setAttribute("value", this.currentValue);
            this.__valueChangedCallback();
            this.__valueIncreasedCallback();
        }

        this.__checkButtonsActivity();
    }

    __checkButtonsActivity() {
        if (this.minimumValue <= this.currentValue - this.step) {
            this.__decreaseButton.disabled = false;
        } else {
            this.__decreaseButton.disabled = true;
        }

        if (this.currentValue + this.step <= this.maximumValue) {
            this.__increaseButton.disabled = false;
        } else {
            this.__increaseButton.disabled = true;
        }
    }
}
