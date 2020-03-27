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
};
