package com.nexaanova.crm.service;

import com.nexaanova.crm.model.UserAccount;
import com.nexaanova.crm.util.ApiException;
import jakarta.servlet.http.HttpServletRequest;

public class Access {
    public static UserAccount user(HttpServletRequest request) {
        return (UserAccount) request.getAttribute("user");
    }

    public static void admin(UserAccount user) {
        if (!"ADMIN".equals(user.getRole())) {
            throw new ApiException(403, "Only an Admin can perform this action.");
        }
    }

    public static void writer(UserAccount user) {
        if ("MANAGER".equals(user.getRole())) {
            throw new ApiException(403, "The Manager account has read-only access.");
        }
    }
}
