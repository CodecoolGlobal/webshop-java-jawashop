export let template = {
    forEnabledProduct: function (product) {
        return `
            <div class="col col-sm-12 col-md-6 col-lg-4">
                <div class="card card-container card-vertical">
                    <img src="/static/img/uploads/${product.id}.jpg" class="product-image" alt="Image of the product">
                    <div class="card-header">
                        <h4 class="card-title">${product.name}</h4>
                        <p class="card-text">${product.description}</p>
                    </div>
                    <div class="card-body">
                        <div class="card-text">
                            <p class="lead">${product.price.toFixed(1)} ${product.currency.code}</p>
                            <div>
                                <a class="btn btn-dark add-to-cart-btn" data-id="${product.id}">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `
    },

    forDisabledProduct: function (product) {
        return `
            <div class="col col-sm-12 col-md-6 col-lg-4">
                <div class="card card-container card-vertical">
                    <img src="/static/img/uploads/${product.id}.jpg" class="product-image" alt="Image of the product">
                    <div class="card-header">
                        <h4 class="card-title">${product.name}</h4>
                        <p class="card-text">${product.description}</p>
                    </div>
                    <div class="card-body">
                        <div class="card-text">
                            <p class="lead">${product.price.toFixed(1)} ${product.currency.code}</p>
                            <button type="button" class="btn btn-secondary disabled" data-toggle="tooltip" data-placement="top" title="Login required">
                                Add to cart
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        `
    },
};
