export let template = {

    dropdownTemplate : function(filterOption) {
        return `
            <div class="dropdown-option">
                <a class="dropdown-item" data-id="${filterOption.id}">${filterOption.name}</a>
            </div>
        `
    },

    productTemplate : function (product) {
        return `
            <div class="col col-sm-12 col-md-6 col-lg-4">
                <div class="card card-container">
                    <img src="/static/img/uploads/${product.id}.jpg" alt="Image of the product">
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
};
