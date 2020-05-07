export let template = {
    forOrder: function(order) {
        console.log(order)
        return `
            <div class="card-container m-3 p-3 rounded">
                <h3>Ordered: ${order.date}</h3>
                <h5>Total price: ${order.total_price}</h5>
                <h5>Products:</h5>
                ${order.items.map(template.__forOrderedProduct).join("")}
            </div>
        `
    },

    __forOrderedProduct: function(orderedProduct) {
        return `
            <div class="row p-3 pl-5">
                <div class="col">
                    <span>${orderedProduct.product.name}</span>
                    <span>${orderedProduct.product.price} JPY</span>
                    <span>${orderedProduct.quantity}</span>
                </div>
            </div>
        `
    },
};
