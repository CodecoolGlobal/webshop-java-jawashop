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
                                <a class="btn btn-dark" href="#">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </div>        
            </div>
        `
    }
};
