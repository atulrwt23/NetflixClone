package my.netflix.clone.controller;

import my.netflix.clone.entity.User;
import my.netflix.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserManagementController {

    private final UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/db-connectivit")
    public ResponseEntity<String> testDatabaseConnectivity() {
        try {
            // Try to count users in the database
            return ResponseEntity.ok("Database connectivity successful! User count: " + userService.count());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Database connectivity failed: " + e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createTestUser() {
        try {
            return ResponseEntity.ok(userService.createUser());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
