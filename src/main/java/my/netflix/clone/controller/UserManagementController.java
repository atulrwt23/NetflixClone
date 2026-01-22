package my.netflix.clone.controller;

import my.netflix.clone.entity.User;
import my.netflix.clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserManagementController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/db-connectivit")
    public ResponseEntity<String> testDatabaseConnectivity() {
        try {
            // Try to count users in the database
            long userCount = userRepository.count();
            return ResponseEntity.ok("Database connectivity successful! User count: " + userCount);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Database connectivity failed: " + e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createTestUser() {
        try {
            User testUser = new User();
            testUser.setUsername("test_user_" + System.currentTimeMillis());
            testUser.setEmail("test@example.com");
            testUser.setPassword("password123");

            User savedUser = userRepository.save(testUser);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
