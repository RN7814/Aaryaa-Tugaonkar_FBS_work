package com.nexaanova.crm.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

public class Validation {
    public static String text(String value, String label, int max, boolean required) {
        String result = value == null ? "" : value.trim();
        if ((required && result.isEmpty()) || result.length() > max) {
            throw new ApiException(400, label + " is required and must be at most " + max + " characters.");
        }
        return result;
    }

    public static String email(String value, boolean required) {
        String result = text(value, "Email", 150, required).toLowerCase(Locale.ROOT);
        if (!result.isEmpty() && !result.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new ApiException(400, "Please enter a valid email address.");
        }
        return result;
    }

    public static String phone(String value) {
        String result = text(value, "Phone", 30, true).replaceAll("[\\s()\\-]", "");
        if (!result.matches("\\+?[0-9]{10,15}")) {
            throw new ApiException(400, "Phone must contain 10 to 15 digits, with an optional + country code.");
        }
        // Indian local and +91 forms should not create duplicate records.
        if (result.matches("[0-9]{10}")) {
            result = "+91" + result;
        } else if (result.matches("91[0-9]{10}")) {
            result = "+" + result;
        }
        return result;
    }

    public static String choice(String value, String label, List<String> choices) {
        if (value == null || !choices.contains(value)) {
            throw new ApiException(400, "Please choose a valid " + label + ".");
        }
        return value;
    }

    public static BigDecimal money(BigDecimal value, String label, boolean allowZero) {
        if (value == null || value.signum() < 0 || (!allowZero && value.signum() == 0)
                || value.compareTo(new BigDecimal("99999999.99")) > 0) {
            throw new ApiException(400, label + " is outside the allowed range.");
        }
        try {
            return value.setScale(2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
            throw new ApiException(400, label + " must have at most two decimal places.");
        }
    }

    public static void password(String value) {
        if (value == null || value.length() < 12 || value.length() > 128) {
            throw new ApiException(400, "Password must be 12 to 128 characters.");
        }
    }
}
