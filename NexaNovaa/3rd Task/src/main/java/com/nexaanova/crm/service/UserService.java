package com.nexaanova.crm.service;

import com.nexaanova.crm.dao.UserDao;
import com.nexaanova.crm.model.UserAccount;
import com.nexaanova.crm.model.UserRequest;
import com.nexaanova.crm.util.ApiException;
import com.nexaanova.crm.util.CrmOptions;
import com.nexaanova.crm.util.PasswordHelper;
import com.nexaanova.crm.util.Validation;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao users;
    private final String dummyHash = PasswordHelper.hash("Unused-dummy-password-123!");

    public UserService(UserDao users) { this.users = users; }

    public UserAccount authenticate(String email, String password) {
        if (password == null || password.length() > 128) {
            throw new ApiException(401, "Email or password is incorrect.");
        }
        String address = email == null ? "" : email.trim().toLowerCase(Locale.ROOT);
        UserAccount user = users.findByEmail(address);
        String hash = user == null ? dummyHash : users.findPasswordHash(user.getId());
        boolean matches = PasswordHelper.matches(password, hash);
        if (!matches || user == null || !user.getActive()) {
            throw new ApiException(401, "Email or password is incorrect.");
        }
        return user;
    }

    public UserAccount create(UserRequest request, UserAccount actor) {
        Access.admin(actor);
        Validation.password(request.getPassword());
        UserAccount user = new UserAccount();
        user.setName(Validation.text(request.getName(), "Name", 100, true));
        user.setEmail(Validation.email(request.getEmail(), true));
        user.setRole(Validation.choice(request.getRole(), "role", CrmOptions.ROLES));
        long id = users.create(user, PasswordHelper.hash(request.getPassword()));
        return users.findById(id);
    }

    public UserAccount setActive(long id, boolean active, UserAccount actor) {
        Access.admin(actor);
        if (id == actor.getId()) {
            throw new ApiException(400, "You cannot disable your own account.");
        }
        UserAccount user = users.findById(id);
        if (user == null) { throw new ApiException(404, "User was not found."); }
        users.setActive(id, active);
        return users.findById(id);
    }
}
