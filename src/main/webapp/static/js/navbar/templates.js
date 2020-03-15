export let template = {
    categoryDropdownTemplate : function(category) {
        return `
            <a class="dropdown-item category-dropdown-item" data-id="${category.id}">${category.name}</a>
        `
    },

    supplierDropdownTemplate: function(supplier) {
        return `
            <a class="dropdown-item supplier-dropdown-item" data-id="${supplier.id}">${supplier.name}</a>
        `
    },

    forShoppingCartButton : function(cart) {
        if (0 < cart.item_count) {
            return `You have <span class="text-info">${cart.item_count}</span> item in your cart.<br>
                    Total price: <span class="text-info">${cart.total_value} ${cart.currency}</span>.`
        } else {
            return `You have <span class="text-info">${cart.item_count}</span> item in your cart.`
        }
    },
}
