// Generates the needed HTML

import { template } from "./templates.js";
import { dataHandler } from "./data_handler.js";
import NumberPicker from "./ui/components/number-picker.js";

export let ui = {
    getViewRoot: function() {
        return document.getElementById("content-container");
    },

    createIndexPage: function(response) {
        ui.createProductsPage(response);
        ui.registerShoppingCartEventLister();
    },

    createProductsPage: function(response) {
        const viewRoot = ui.getViewRoot();
        viewRoot.innerHTML = "";
        const root = document.createElement("div");
        root.classList.add("row");
        const products = response.products;
        root.innerHTML = products.map(template.productTemplate).join("");
        viewRoot.appendChild(root);
        ui.addToShoppingCartEventListener();
    },

    createCategoryDropdown: function(response) {
        const dropdownCategoryRoot = document.getElementById("category-dropdown");
        const categories = response.categories;
        dropdownCategoryRoot.innerHTML = categories.map(template.categoryDropdownTemplate).join("");
        ui.addCategoryEventListener();
    },

    createSupplierDropdown: function(response) {
        const dropdownCategoryRoot = document.getElementById("supplier-dropdown");
        const suppliers = response.suppliers;
        dropdownCategoryRoot.innerHTML = suppliers.map(template.supplierDropdownTemplate).join("");
        ui.addSupplierEventListener();
    },

    addCategoryEventListener: function () {
        const filterOptions = document.querySelectorAll(".category-dropdown-item");
        filterOptions.forEach(filterOption => filterOption.addEventListener("click", function () {
            const categoryId = this.dataset.id;
            dataHandler.getProductsByCategory(categoryId, ui.createProductsPage);
        }))
    },

    addSupplierEventListener: function () {
        const filterOptions = document.querySelectorAll(".supplier-dropdown-item");
        filterOptions.forEach(filterOption => filterOption.addEventListener("click", function () {
            const supplierId = this.dataset.id;
            dataHandler.getProductsBySupplier(supplierId, ui.createProductsPage);
        }))
    },
  
    addToShoppingCartEventListener : function() {
        const buttons = document.querySelectorAll(".add-to-cart-btn");
        buttons.forEach(button => button.addEventListener("click", function(){
            const productId = this.dataset.id;
            dataHandler.addToShoppingCart(productId, ui.updateCartButtonStats);
        }))
    },

    updateCartButtonStats: function(response) {
        const mobileNode = document.querySelector("#cart-item-total-value-mobile");
        mobileNode.innerHTML = template.forShoppingCartButton(response.message);

        const totalValueNode = document.querySelector("#cart-item-total-value");
        totalValueNode.innerHTML = response.message.cart.total_value;

        const cartItemCounterNode = document.querySelector("#cart-item-counter");
        cartItemCounterNode.innerHTML = response.message.cart.item_count;
    },

    registerShoppingCartEventLister: function() {
        const cartButton = document.querySelector("#cart-button");
        cartButton.addEventListener("click", function() {
            dataHandler.getShoppingCartProducts(ui.renderShoppingCartMenu);
        });
    },

    renderShoppingCartMenu: function(response) {
        const htmlRoot = ui.getViewRoot()
        htmlRoot.innerHTML = response.products.map(template.forCartProduct).join("");

        const numberPickerNodes = document.querySelectorAll(".number-picker");

        numberPickerNodes.forEach(function(numberPickerNode, i) {
            const product = response.products[i];
            let numberPicker = new NumberPicker();
            numberPicker.drawInto(numberPickerNode);

            const priceNode = numberPickerNode.parentNode.parentNode.querySelector("div");
            priceNode.textContent = `${product.price} / count`;

            const totalValueNode = numberPickerNode.parentNode.parentNode.querySelector("div.text-center:last-child");
            totalValueNode.textContent = `total: ${numberPicker.currentValue * parseFloat(product.price)} ${product.currency}`;
            numberPicker.onValueChangedEvent(function() {
                totalValueNode.textContent = `total: ${numberPicker.currentValue * parseFloat(product.price)} ${product.currency}`;
            });

            numberPicker.onValueIncreasedEvent(function() { console.log("+1"); });
            numberPicker.onValueDecreasedEvent(function() { console.log("-1"); });
        });
    },
};
