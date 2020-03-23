// Glue between the bigger modules

import { logic as errorHandler } from "./error/logic.js";
import { navbar } from "./navbar/logic.js";
import { logic as homepage } from "./index/logic.js";

async function init() {

    errorHandler.init();

    navbar.init();
    homepage.init();

    /*await Promise.all([
        navbar.init(),
        homepage.init(),
    ]);*/
};

init();
