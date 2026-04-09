package org.example.validation;

import org.example.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class UserValidator {

    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private UserValidator() {}

    public static List<String> validate(User user) {
        if (user == null) {
            return List.of("User must not be null");
        }

        List<String> errors = new ArrayList<>();

        if (user.getName() == null || user.getName().isBlank()) {
            errors.add("Name is required");
        } else if (user.getName().length() > 100) {
            errors.add("Name must not exceed 100 characters");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            errors.add("Email is required");
        } else if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            errors.add("Email format is invalid");
        }

        return Collections.unmodifiableList(errors);
    }

    public static boolean isValid(User user) {
        return validate(user).isEmpty();
    }
}
