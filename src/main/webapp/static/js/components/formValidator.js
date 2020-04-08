export let formValidator = {
    isValidFullName: function(input) {
        const parts = input.split(" ");

        if (!formValidator.__hasExactLength(2, parts)) {
            return false;
        }

        if (!formValidator.__hasLengthBetween(3, 10, parts[0])) {
            return false;
        }

        if (!formValidator.__hasLengthBetween(3, 20, parts[1])) {
            return false;
        }

        return true;
    },

    isValidEmail: function(input) {
        // https://stackoverflow.com/a/46181/7306734
        const regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return regex.test(String(input).toLowerCase());
    },

    isValidPhoneNumber: function(input) {
        input = input.replace(/[+ /]/g, "");

        if (!formValidator.__hasExactLength(11, input)) {
            return false;
        }

        return /[0-9]{11}/g.test(input);
    },

    isValidCountry: function(input) {
        return formValidator.__hasLengthBetween(4, 15, input);
    },

    isValidCity: function(input) {
        return formValidator.__hasLengthBetween(1, 25, input);
    },

    isValidZipCode: function(input) {
        return formValidator.__hasLengthBetween(2, 10, input);
    },

    isValidAddress: function(input) {
        return formValidator.__hasLengthBetween(5, 30, input);
    },

    isValidPassword: function(input) {
        if (!formValidator.__isString(input)) {
            return false;
        }

        return 8 <= input.length;
    },

    __isString: function(input) {
        // https://stackoverflow.com/a/9436948/7306734
        return typeof input === 'string' || input instanceof String;
    },

    __hasExactLength: function(value, input) {
        return input.length === value;
    },

    __hasLengthBetween(min, max, input) {
        if (!formValidator.__isString(input)) {
            return false;
        }

        return min <= input.length && input.length <= max;
    }
};
