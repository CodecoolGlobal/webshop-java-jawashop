export function SupplierView(routingManager) {
    this.routingManager = routingManager;
    routingManager.install('/suppliers/all', this.renderAll.bind(this));
}

SupplierView.prototype.renderAll = function() {
    this.routingManager.setRoute('/suppliers/all');
    const appRoot = document.getElementById('app');
    appRoot.innerHTML = "";

    const productContainer = document
          .getElementById('product-container-template')
          .content
          .cloneNode(true);


    appRoot.innerHTML = "<span id='btn'>View all Products</span>";

    const addToChartBtn = appRoot.querySelector("span#btn");
    const view = this;
    addToChartBtn.addEventListener("click", function() {
            view.routingManager.goTo('/products/all');
    });
}

function productToElement(product) {
    const fragment = document
          .getElementById('product-template')
          .content
          .cloneNode(true);

    fragment.querySelector('.product-name').innerText = product.name;
    fragment.querySelector('.product-description').innerText = product.description;
    fragment.querySelector('.product-picture').src = product.picUrl;
    fragment.querySelector('.product-price').innerText = product.price;

    return fragment;
}
