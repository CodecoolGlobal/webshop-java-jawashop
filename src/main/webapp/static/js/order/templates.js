export let template = {
    forOrder: function(order) {
        return `
            <div class="col-3"></div>
            <div class="col-12 col-lg-6 card-container m-3 p-3 rounded mx-auto">
                <div class="row">
                    <div class="col-12 col-sm-6 col-lg-12 col-xl-6">
                        Order status: <span class="text-primary">Pending</span>
                    </div>
                    <div class="col-12 col-sm-6 col-lg-12 col-xl-6">
                        Ordered at: <span class="text-primary">${order.date.substring(0, 16)}</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        Total price: <span class="text-primary">${order.total_price} JPY</span>
                    </div>
                </div>
                
                <h5 class="mt-4">Ordered products:</h5>
                ${order.items.map(template.__forOrderedProduct).join("")}
            </div>
            <div class="col-3"></div>
        `
    },

    __forOrderedProduct: function(orderedProduct) {
        return `
            <div class="row p-3 pl-5">
                <div class="col">(${orderedProduct.quantity}x) ${orderedProduct.product.name}</div>
                <div class="col text-right">${orderedProduct.product.price * orderedProduct.quantity} JPY</div>
            </div>
        `
    },
};
