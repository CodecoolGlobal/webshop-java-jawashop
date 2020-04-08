import { dataHandler } from "./data_handler.js";
import { navbar } from "../navbar/logic.js";

export let logic = {
    execute: function() {
        dataHandler.logout(function(response) {
            if (response === "Successful logout") {
                navbar.loggedOut();
            }
        });
    },
};
