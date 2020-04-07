package com.codecool.shop;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern emailRegex;

    static {
        emailRegex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
    }

    public boolean isValidFullName(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 2) {
            return false;
        }

        if (!hasLengthBetween(3, 10, parts[0])) {
            return false;
        }

        if (!hasLengthBetween(3, 20, parts[1])) {
            return false;
        }

        return true;
    }

    public boolean isValidEmail(String input) {
        // https://stackoverflow.com/a/8204703/7306734
        return emailRegex.matcher(input).matches();
    }

    public boolean isValidPhoneNumber(String input) {
        try {
            long phoneNumber = Long.parseLong(input.substring(0, 11));
            return 11_000_000_000L <= phoneNumber && phoneNumber <= 99_999_999_999L;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isValidCountry(String input) {
        return hasLengthBetween(4, 15, input);
    }

    public boolean isValidCity(String input) {
        return hasLengthBetween(1, 25, input);
    }

    public boolean isValidZipCode(String input) {
        return hasLengthBetween(2, 10, input);
    }

    public boolean isValidAddress(String input) {
        return hasLengthBetween(5, 30, input);
    }

    public boolean isValidPassword(String input) {
        return 8 <= input.length();
    }

    private boolean hasLengthBetween(int min, int max, String input) {
        return min <= input.length() && input.length() <= max;
    }
}
