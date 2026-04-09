package exception;

import org.example.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserNotFoundException")
class UserNotFoundExceptionTest {

    @Test
    @DisplayName("should contain user id in message")
    void messageContainsId() {
        UserNotFoundException ex = new UserNotFoundException(42L);

        assertEquals(42L, ex.getUserId());
        assertTrue(ex.getMessage().contains("42"));
    }

    @Test
    @DisplayName("should be a RuntimeException")
    void isRuntimeException() {
        assertInstanceOf(RuntimeException.class, new UserNotFoundException(1L));
    }
}
