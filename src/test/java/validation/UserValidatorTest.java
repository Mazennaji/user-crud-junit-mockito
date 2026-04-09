package validation;

import org.example.model.User;
import org.example.validation.UserValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserValidator")
class UserValidatorTest {

    @Test
    @DisplayName("valid user should produce no errors")
    void validUser() {
        User user = new User(1L, "Alice", "alice@example.com");
        assertTrue(UserValidator.isValid(user));
        assertTrue(UserValidator.validate(user).isEmpty());
    }

    @Test
    @DisplayName("null user should return error")
    void nullUser() {
        List<String> errors = UserValidator.validate(null);
        assertEquals(1, errors.size());
        assertTrue(errors.get(0).contains("null"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    @DisplayName("blank or null name should be invalid")
    void invalidName(String name) {
        User user = new User(1L, name, "alice@example.com");
        assertFalse(UserValidator.isValid(user));
        assertTrue(UserValidator.validate(user).stream().anyMatch(e -> e.contains("Name")));
    }

    @Test
    @DisplayName("name exceeding 100 chars should be invalid")
    void longName() {
        String longName = "A".repeat(101);
        User user = new User(1L, longName, "alice@example.com");
        assertFalse(UserValidator.isValid(user));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    @DisplayName("blank or null email should be invalid")
    void invalidEmail(String email) {
        User user = new User(1L, "Alice", email);
        assertFalse(UserValidator.isValid(user));
    }

    @ParameterizedTest
    @ValueSource(strings = {"notanemail", "missing@", "@nodomain", "spaces in@email.com"})
    @DisplayName("malformed emails should be invalid")
    void malformedEmail(String email) {
        User user = new User(1L, "Alice", email);
        assertFalse(UserValidator.isValid(user));
    }

    @ParameterizedTest
    @ValueSource(strings = {"user@domain.com", "test+tag@sub.domain.org", "a.b@c.co"})
    @DisplayName("well-formed emails should be valid")
    void validEmails(String email) {
        User user = new User(1L, "Alice", email);
        assertTrue(UserValidator.isValid(user));
    }

    @Test
    @DisplayName("multiple errors should all be reported")
    void multipleErrors() {
        User user = new User(1L, null, null);
        List<String> errors = UserValidator.validate(user);
        assertEquals(2, errors.size());
    }
}
