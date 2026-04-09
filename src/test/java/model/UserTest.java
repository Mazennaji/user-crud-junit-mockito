package model;

import org.example.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User")
class UserTest {

    @Test
    @DisplayName("users with same id should be equal")
    void equalsSameId() {
        User a = new User(1L, "Alice", "alice@example.com");
        User b = new User(1L, "Bob", "bob@example.com");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("users with different ids should not be equal")
    void equalsDifferentId() {
        User a = new User(1L, "Alice", "alice@example.com");
        User b = new User(2L, "Alice", "alice@example.com");
        assertNotEquals(a, b);
    }

    @Test
    @DisplayName("user should not equal null")
    void equalsNull() {
        User a = new User(1L, "Alice", "alice@example.com");
        assertNotEquals(null, a);
    }

    @Test
    @DisplayName("user should not equal different type")
    void equalsDifferentType() {
        User a = new User(1L, "Alice", "alice@example.com");
        assertNotEquals("not a user", a);
    }

    @Test
    @DisplayName("getters and setters should work")
    void gettersSetters() {
        User user = new User();
        user.setId(5L);
        user.setName("Eve");
        user.setEmail("eve@example.com");

        assertEquals(5L, user.getId());
        assertEquals("Eve", user.getName());
        assertEquals("eve@example.com", user.getEmail());
    }
}
