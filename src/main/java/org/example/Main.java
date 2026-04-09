package org.example;

import org.example.model.User;
import org.example.repository.InMemoryUserRepository;
import org.example.service.UserService;

public class Main {

    public static void main(String[] args) {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        UserService service = new UserService(repository);

        User alice = service.createUser(new User(null, "Alice", "alice@example.com"));
        User bob = service.createUser(new User(null, "Bob", "bob@example.com"));
        System.out.println("Created: " + alice.getName() + " (id=" + alice.getId() + ")");
        System.out.println("Created: " + bob.getName() + " (id=" + bob.getId() + ")");

        service.getUserById(1L).ifPresent(u ->
            System.out.println("Found: " + u.getName() + " <" + u.getEmail() + ">"));

        User updated = service.updateUser(1L, new User(null, "Alice Smith", "alice.smith@example.com"));
        System.out.println("Updated: " + updated.getName() + " <" + updated.getEmail() + ">");

        System.out.println("All users: " + service.getAllUsers().size());

        service.deleteUser(2L);
        System.out.println("After delete: " + service.getAllUsers().size() + " user(s) remaining");
    }
}
