import { dataHandler as productsDAL } from "./../products/data_handler.js";

export let dataHandler = {
    getAllProduct: function(callback) {
        return productsDAL.getAll(callback);
    },

    addToShoppingCart: function(productId, callback) {
        return productsDAL.addToShoppingCart(productId, callback);
    },

    getProductsByCategory: function (id, callback) {
        return productsDAL.getProductsByCategory(id, callback);
    },

    getProductsBySupplier(id, callback) {
        return productsDAL.getProductsBySupplier(id, callback);
    },
}
