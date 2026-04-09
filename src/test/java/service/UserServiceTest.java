package service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = new User(1L, "Alice", "alice@example.com");
    }

    @Nested
    @DisplayName("createUser")
    class CreateUser {

        @Test
        @DisplayName("should save and return user")
        void happyPath() {
            when(userRepository.save(any(User.class))).thenReturn(sampleUser);

            User result = userService.createUser(sampleUser);

            assertEquals("Alice", result.getName());
            verify(userRepository, times(1)).save(sampleUser);
        }

        @Test
        @DisplayName("should throw when name is blank")
        void blankName() {
            sampleUser.setName("  ");
            assertThrows(IllegalArgumentException.class, () -> userService.createUser(sampleUser));
            verify(userRepository, never()).save(any());
        }

        @Test
        @DisplayName("should throw when email is null")
        void nullEmail() {
            sampleUser.setEmail(null);
            assertThrows(IllegalArgumentException.class, () -> userService.createUser(sampleUser));
        }
    }

    @Nested
    @DisplayName("getUserById")
    class GetUserById {

        @Test
        @DisplayName("should return user when found")
        void found() {
            when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));

            Optional<User> result = userService.getUserById(1L);

            assertTrue(result.isPresent());
            assertEquals("Alice", result.get().getName());
        }

        @Test
        @DisplayName("should return empty when not found")
        void notFound() {
            when(userRepository.findById(99L)).thenReturn(Optional.empty());

            assertTrue(userService.getUserById(99L).isEmpty());
        }
    }

    @Test
    @DisplayName("getAllUsers should return list from repository")
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(sampleUser));

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("getAllUsers should return empty list when no users")
    void getAllUsersEmpty() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(userService.getAllUsers().isEmpty());
    }

    @Nested
    @DisplayName("updateUser")
    class UpdateUser {

        @Test
        @DisplayName("should update existing user")
        void happyPath() {
            User updated = new User(null, "Bob", "bob@example.com");
            when(userRepository.existsById(1L)).thenReturn(true);
            when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

            User result = userService.updateUser(1L, updated);

            assertEquals(1L, result.getId());
            assertEquals("Bob", result.getName());
        }

        @Test
        @DisplayName("should throw when user not found")
        void notFound() {
            when(userRepository.existsById(99L)).thenReturn(false);

            assertThrows(IllegalArgumentException.class,
                () -> userService.updateUser(99L, sampleUser));
            verify(userRepository, never()).save(any());
        }
    }

    @Nested
    @DisplayName("deleteUser")
    class DeleteUser {

        @Test
        @DisplayName("should delete existing user")
        void happyPath() {
            when(userRepository.existsById(1L)).thenReturn(true);

            userService.deleteUser(1L);

            verify(userRepository).deleteById(1L);
        }

        @Test
        @DisplayName("should throw when user not found")
        void notFound() {
            when(userRepository.existsById(99L)).thenReturn(false);

            assertThrows(IllegalArgumentException.class,
                () -> userService.deleteUser(99L));
            verify(userRepository, never()).deleteById(any());
        }
    }
}
