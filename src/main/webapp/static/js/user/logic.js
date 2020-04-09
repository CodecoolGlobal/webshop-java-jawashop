export let logic = {
    __user: { },

    checkAuthentication: function() {
        const cookies = document.cookie.split(";");

        for (let cookie of cookies) {
            cookie = cookie.split("=");
            if (cookie.length === 2 && cookie[0] === "auth_token" && 30 < cookie[1].length) {
                logic.__user.auth_token = cookie[1];
                break;
            }
        }
    },

    isAuthenticated: function() {
        return logic.__user.auth_token !== undefined && logic.__user.auth_token !== null;
    },

    setLoggedOut: function() {
        logic.__user.auth_token = null;
        logic.checkAuthentication();
    },
};
