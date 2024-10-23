package com.devminrat.tennis.util;

public final class ValidateUtil {
    private ValidateUtil() {
    }

    public static boolean isValidValues(String... values) {
        if (values == null) {
            return false;
        }

        for (String value : values) {
            if (!isValidValue(value)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidValue(final String value) {
        return value != null && !value.isBlank();
    }
}
