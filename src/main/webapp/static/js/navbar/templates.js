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

}
