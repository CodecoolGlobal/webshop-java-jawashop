import { logic as errorHandler } from "./error/logic.js";
import { logic as homepage } from "./index/logic.js";
import { logic as user } from "./user/logic.js";
import { navbar } from "./navbar/logic.js";
import { ui as header } from "./components/header.js";
import { ui as body } from "./components/body.js";

function init() {
    header.render(async function() {
        body.init();

        errorHandler.init();

        await user.checkAuthentication();

        await navbar.init();

        homepage.init();
    });
}

init();
