package com.nexaanova.crm;

import com.nexaanova.crm.util.ApiException;
import com.nexaanova.crm.util.PasswordHelper;
import com.nexaanova.crm.util.Validation;
import java.math.BigDecimal;
import java.util.List;

// Ordinary Java checks: run this main method, without a separate test framework.
public class CoreChecks {
    private static int passed;

    public static void main(String[] args) {
        check(Validation.phone("98765 43210").equals("+919876543210"), "Indian phone normalization");
        check(Validation.phone("(+91) 98765-43210").equals(Validation.phone("919876543210")), "Duplicate phone representations");
        rejects(() -> Validation.phone("hello"), "Non-numeric phone rejected");
        check(Validation.email(" STUDENT@Example.COM ", true).equals("student@example.com"), "Email normalization");
        rejects(() -> Validation.email("student@", true), "Invalid email rejected");
        rejects(() -> Validation.text("   ", "Name", 100, true), "Blank required field rejected");
        check(Validation.money(new BigDecimal("12.30"), "Fees", false).equals(new BigDecimal("12.30")), "Money preserves paise");
        check(Validation.money(BigDecimal.ZERO, "Paid", true).equals(new BigDecimal("0.00")), "Zero initial payment allowed");
        rejects(() -> Validation.money(new BigDecimal("12.345"), "Fees", false), "Fractional paise rejected");
        rejects(() -> Validation.money(new BigDecimal("-1"), "Paid", true), "Negative payment rejected");
        rejects(() -> Validation.choice("UNKNOWN", "Stage", List.of("OPEN")), "Invalid choices rejected");
        rejects(() -> Validation.password("short"), "Short password rejected");
        String first = PasswordHelper.hash("A sufficiently long password");
        String second = PasswordHelper.hash("A sufficiently long password");
        check(!first.equals(second), "Independent random password salts");
        check(PasswordHelper.matches("A sufficiently long password", first), "Password verification");
        check(!PasswordHelper.matches("Wrong password", first), "Wrong password rejected");
        check(!PasswordHelper.matches("anything", "bad-hash"), "Malformed hash rejected");
        check(!PasswordHelper.matches(null, null), "Missing password rejected");
        System.out.println("PASS: " + passed + " ordinary Java checks.");
    }

    private static void check(boolean condition, String name) {
        if (!condition) { throw new AssertionError(name); }
        passed++;
    }

    private static void rejects(Runnable operation, String name) {
        try { operation.run(); } catch (ApiException expected) { check(expected.getStatus() == 400, name); return; }
        throw new AssertionError(name);
    }
}
