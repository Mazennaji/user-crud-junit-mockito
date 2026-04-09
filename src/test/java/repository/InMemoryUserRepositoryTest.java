package repository;

import org.example.model.User;
import org.example.repository.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InMemoryUserRepository")
class InMemoryUserRepositoryTest {

    private InMemoryUserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryUserRepository();
    }

    @Test
    @DisplayName("save should auto-generate id when null")
    void saveAutoId() {
        User user = new User(null, "Alice", "alice@example.com");
        User saved = repository.save(user);

        assertNotNull(saved.getId());
        assertEquals(1L, saved.getId());
    }

    @Test
    @DisplayName("save should preserve explicit id")
    void saveExplicitId() {
        User user = new User(42L, "Bob", "bob@example.com");
        User saved = repository.save(user);

        assertEquals(42L, saved.getId());
    }

    @Test
    @DisplayName("findById should return saved user")
    void findByIdFound() {
        repository.save(new User(null, "Alice", "alice@example.com"));

        Optional<User> found = repository.findById(1L);

        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getName());
    }

    @Test
    @DisplayName("findById should return empty for missing id")
    void findByIdNotFound() {
        assertTrue(repository.findById(999L).isEmpty());
    }

    @Test
    @DisplayName("findAll should return all saved users")
    void findAll() {
        repository.save(new User(null, "Alice", "a@example.com"));
        repository.save(new User(null, "Bob", "b@example.com"));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    @DisplayName("deleteById should remove the user")
    void deleteById() {
        repository.save(new User(null, "Alice", "a@example.com"));
        repository.deleteById(1L);

        assertFalse(repository.existsById(1L));
    }

    @Test
    @DisplayName("existsById should return true for existing user")
    void existsByIdTrue() {
        repository.save(new User(null, "Alice", "a@example.com"));
        assertTrue(repository.existsById(1L));
    }

    @Test
    @DisplayName("existsById should return false for missing user")
    void existsByIdFalse() {
        assertFalse(repository.existsById(1L));
    }

    @Test
    @DisplayName("clear should remove all users and reset id counter")
    void clear() {
        repository.save(new User(null, "Alice", "a@example.com"));
        repository.save(new User(null, "Bob", "b@example.com"));

        repository.clear();

        assertEquals(0, repository.findAll().size());
        User next = repository.save(new User(null, "Charlie", "c@example.com"));
        assertEquals(1L, next.getId(), "ID counter should reset to 1");
    }
}
