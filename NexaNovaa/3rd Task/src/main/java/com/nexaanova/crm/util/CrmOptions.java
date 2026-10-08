package com.nexaanova.crm.util;

import java.util.Arrays;
import java.util.List;

// The frontend loads these choices from /api/options.
public class CrmOptions {
    public static final List<String> ROLES = Arrays.asList("ADMIN", "COUNSELOR", "MANAGER");
    public static final List<String> STAGES = Arrays.asList("OPEN", "CNR", "CALL_BACK", "INTERESTED", "FOLLOW_UP", "CONVERTED", "CLOSED");
    public static final List<String> SOURCES = Arrays.asList("Walk-in", "Website", "Instagram", "Meta", "Google", "College", "Referral", "Other");
    public static final List<String> OUTCOMES = Arrays.asList("INTERESTED", "NO_ANSWER", "CALL_LATER", "NOT_INTERESTED");
    public static final List<String> PAYMENTS = Arrays.asList("Cash", "Online", "Cheque", "DD");
}
