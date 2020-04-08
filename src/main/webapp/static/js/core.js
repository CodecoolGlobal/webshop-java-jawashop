import { logic as errorHandler } from "./error/logic.js";
import { navbar } from "./navbar/logic.js";
import { logic as homepage } from "./index/logic.js";
import { logic as user } from "./user/logic.js";

async function init() {
    errorHandler.init();

    user.checkAuthentication();

    navbar.init();

    homepage.init();
}

init();
