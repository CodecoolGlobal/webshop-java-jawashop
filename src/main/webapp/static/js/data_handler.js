export let dataHandler = {
    __jsonParser: null,
    __errorChecker: null,

    get: function (url, callback) {
        fetch(url, {
            method: 'GET',
            credentials: 'same-origin'
        })
        .then(response => dataHandler.__jsonParser(response))
        .then(jsonResponse => callback(jsonResponse))
        .catch(err => dataHandler.__errorChecker(url, err));
    },

    post: function (url, data, callback) {
        fetch(url, {
            method: 'POST',
            credentials: 'same-origin',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => dataHandler.__jsonParser(response))
        .then(jsonResponse => callback(jsonResponse))
        .catch(err => dataHandler.__errorChecker(url, err));
    },

    delete: function (url, data, callback) {
        fetch(url, {
            method: 'DELETE',
            credentials: 'same-origin',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => dataHandler.__jsonParser(response))
        .then(jsonResponse => callback(jsonResponse))
        .catch(err => dataHandler.__errorChecker(url, err));
    },

    setPostMiddleware(jsonParser, errorCheckerCallback) {
        dataHandler.__jsonParser = jsonParser;
        dataHandler.__errorChecker = errorCheckerCallback;
    },
};
