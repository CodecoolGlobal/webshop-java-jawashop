export let dataHandler = {
    get: function (url) {
        return fetch(url, {
            method: 'GET',
            credentials: 'same-origin'
        })
        .then(response => { return response.json(); });
    },

    post: function (url, data) {
        return fetch(url, {
            method: 'POST',
            credentials: 'same-origin',
            body: JSON.stringify(Object.fromEntries(data)),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => { return response.json(); });
    },
}
