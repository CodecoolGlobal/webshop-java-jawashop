export let template = {

    categoryDropdownTemplate : function(category) {
        return `
            <div class="dropdown-option">
                <a class="category-dropdown-item" data-id="${category.id}">${category.name}</a>
            </div>
        `
    },

    supplierDropdownTemplate: function(supplier) {
        return `
            <div class="dropdown-option">
                <a class="supplier-dropdown-item" data-id="${supplier.id}">${supplier.name}</a>
            </div>
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
                                <a class="btn btn-dark" href="#">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </div>        
            </div>
        `
    }
};
