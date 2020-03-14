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

    productTemplate : function (product) {
        return `
            <div class="col col-sm-12 col-md-6 col-lg-4">
                <div class="card card-container">
                    <img src="/static/img/uploads/${product.id}.jpg" class="product-image" alt="Image of the product">
                    <div class="card-header">
                        <h4 class="card-title">${product.name}</h4>
                        <p class="card-text">${product.description}</p>
                    </div>
                    <div class="card-body">
                        <div class="card-text">
                            <p class="lead">${product.price}</p>
                            <div>
                                <a class="btn btn-dark add-to-cart-btn" data-id="${product.id}">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </div>        
            </div>
        `
    },

    forShoppingCartButton : function(stats) {
        if (0 < stats.cart.item_count) {
            return `You have <span class="text-info">${stats.cart.item_count}</span> item in your cart.<br>
                    Total price: <span class="text-info">${stats.cart.total_value}</span>.`
        } else {
            return `You have <span class="text-info">${stats.cart.item_count}</span> item in your cart.`
        }
    },

    forCartProduct: function(product) {
        return `
            <div class="row card-container m-3 p-3">
                <div class="col-6 col-sm-3">
                    <img class="img-fluid" src="/static/img/uploads/${product.id}.jpg" alt="Image of the product.">
                </div>
                <div class="col-6 col-sm-6">
                    <h4>${product.name}</h4>
                    <h6>${product.description}</h6>
                </div>
                <div class="col-12 col-sm-3 d-flex align-items-center">
                    <div class="m-auto">
                        <div class="text-center"></div>
                        <div class="form-group">
                            <div class="number-picker"></div>
                        </div>
                        <div class="text-center"></div>
                    </div>
                </div>
            </div>
        `
    },

    forNumberPicker: function() {
        return `
            <div class="number-picker row form-group justify-content-center">
                <button class="col-3 form-control fas fa-minus-square p-0"></button>
                <input class="col-4 form-control text-center ml-1 mr-1 p-0" readonly value="0">
                <button class="col-3 form-control fas fa-plus-square p-0"></button>
            </div>
        `
    }
};
