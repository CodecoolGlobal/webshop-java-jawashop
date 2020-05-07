import { dataHandler } from "./../data_handler.js";

export let logic = {
    init: function() {
        dataHandler.setPostMiddleware(
            logic.__parseToJson,
            logic.__errorCheckerCallback);
    },

    __parseToJson: async function(response) {
        const json = await response.json();

        if (json.status === 200) {
            return json.message;
        } else {
            throw `Unsuccessful server response: ${JSON.stringify(json)}`;
        }
    },

    __errorCheckerCallback: function(url, error) {
        console.log(url, error);
        Notiflix.Report.Failure(
            "Critical error",
            "Error occured while communicated with the server! Reloading the page might help!",
            "Ok!");
     },
}
