export let template = {
    forProduct: function(item) {
        return `
            <div class="row card-container m-3 p-3">
                <div class="col-6 col-sm-3">
                    <img class="img-fluid" src="/static/img/uploads/${item.product.id}.jpg" alt="Image of the product.">
                </div>
                <div class="col-6 col-sm-6">
                    <h4>${item.product.name}</h4>
                    <h6>${item.product.description}</h6>
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

    forCheckoutButton: function(itemCount) {
        if (0 < itemCount) {
            return `
                <div class="row m-3">
                    <div class="col card-container p-3 align-middle">
                        <div class="alert alert-info m-0">
                            Are You finished the review? Proceed to 
                            <button type="button" class="btn btn-success checkout-btn">Checkout</button>
                        </div>
                    </div>
                </div>
            `
        } else {
            return `
                <div class="row m-3">
                    <div class="col card-container p-3 align-middle">
                        <div class="alert alert-info m-0 text-center">
                            There is no item in the Shopping Cart!
                        </div>
                    </div>
                </div>
            `
        }
    },
};
