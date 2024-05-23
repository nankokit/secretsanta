package com.example.secretsanta.controller;

import com.example.secretsanta.aop.RequestStats;
import com.example.secretsanta.model.User;
import com.example.secretsanta.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestStats
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private UserService userService;

  @GetMapping("/search")
  public Long searchUserIdByNameAndPassword(
      @RequestParam("name") String name, @RequestParam("password") String password) {
    User user = userService.findByNameAndPassword(name, password);
    if (user != null) {
      return user.getId();
    } else {
      return null; // or handle the case when user is not found
    }
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.createUser(user));
  }

  @PostMapping("/bulk")
  public List<User> bulkCreateUser(@RequestBody List<User> users) {
    return userService.bulkCreateUser(users);
  }

  @PutMapping("/{userId}")
  public User updateUser(@PathVariable Long userId, @RequestBody User user) {
    user.setId(userId);
    return userService.updateUser(userId, user);
  }

  @DeleteMapping("/{userId}")
  public void deleteUser(@PathVariable Long userId) {
    userService.deleteUser(userId);
  }

  @GetMapping("/{userId}")
  public Optional<User> getUserById(@PathVariable Long userId) {
    return userService.getUserById(userId);
  }

  @GetMapping("/getAll")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }
}
