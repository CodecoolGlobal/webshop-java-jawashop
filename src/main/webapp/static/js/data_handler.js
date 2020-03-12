export let dataHandler = {
    _api_get: function (url, callback) {
        fetch(url, {
            method: 'GET',
            credentials: 'same-origin'
        })
        .then(response => response.json())
        .then(json_response => callback(json_response));
    },

    _api_post: function (url, data, callback) {
        fetch(url, {
            method: 'POST',
            credentials: 'same-origin',
            body: JSON.stringify(Object.fromEntries(data)),
            headers: {
              'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(json_response => callback(json_response));
    },

    getIndexPage: function (callback) {
        this._api_get('/product', (response) => {
            callback(response);
        });
    },

    getCategories: function (callback) {
        this._api_get('/category', (response) => {
            callback(response);
        });
    },
    getSuppliers: function (callback) {
        this._api_get('/supplier', (response) => {
            callback(response);
        });
    },
    getProductsByCategory: function (id, callback) {
        this._api_get(`/products-by-category?id=${id}`, (response) => {
            callback(response);
        });
    }
};
