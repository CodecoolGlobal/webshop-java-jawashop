import { dataHandler as productsDAL } from "./../products/data_handler.js";
import { dataHandler as shoppingCartDAL } from "./../shopping-cart/data_handler.js";

export let dataHandler = {
    getAllProduct: function(callback) {
        return productsDAL.getAll(callback);
    },

    addToShoppingCart: function(productId, callback) {
        return shoppingCartDAL.addProduct(productId, callback);
    },

    getProductsByCategory: function (id, callback) {
        return productsDAL.getProductsByCategory(id, callback);
    },

    getProductsBySupplier(id, callback) {
        return productsDAL.getProductsBySupplier(id, callback);
    },
}
