// Glue between the bigger modules

import { navbar } from "./navbar/logic.js";
import { logic as homepage } from "./index/logic.js";

async function init() {

    await Promise.all([
        navbar.init(),
        homepage.init(),
    ]);
};

init();
