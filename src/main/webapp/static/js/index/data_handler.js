import { dataHandler as productsDAL } from "./../products/data_handler.js";

export let dataHandler = {
    getAllProduct: function() {
        return productsDAL.getAll();
    },

    addToShoppingCart: function(productId) {
        return productsDAL.addToShoppingCart(productId);
    },

    getProductsByCategory: function (id) {
        return productsDAL.getProductsByCategory(id);
    },

    getProductsBySupplier(id) {
        return productsDAL.getProductsBySupplier(id);
    },
}
