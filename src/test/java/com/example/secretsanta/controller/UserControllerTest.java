package com.example.secretsanta.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.secretsanta.model.User;
import com.example.secretsanta.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class UserControllerTest {
  @Mock private UserService userService;

  @InjectMocks private UserController userController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateUser() {
    // Arrange
    User user = new User();
    when(userService.createUser(any(User.class))).thenReturn(user);

    // Act
    ResponseEntity<User> response = userController.createUser(user);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(user, response.getBody());
    verify(userService).createUser(user);
  }

  @Test
  void testBulkCreateUser() {
    // Arrange
    List<User> users = new ArrayList<>();
    users.add(new User());
    when(userService.bulkCreateUser(anyList())).thenReturn("Bulk create success");

    // Act
    String response = userController.bulkCreateUser(users);

    // Assert
    assertNotNull(response);
    assertEquals("Bulk create success", response);
    verify(userService).bulkCreateUser(anyList());
  }

  @Test
  void testUpdateUser() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    user.setId(userId);
    when(userService.updateUser(userId, user)).thenReturn(user);

    // Act
    User updatedUser = userController.updateUser(userId, user);

    // Assert
    assertNotNull(updatedUser);
    assertEquals(user, updatedUser);
    verify(userService).updateUser(userId, user);
  }

  @Test
  void testDeleteUser() {
    // Arrange
    Long userId = 1L;

    // Act
    userController.deleteUser(userId);

    // Assert
    verify(userService).deleteUser(userId);
  }

  @Test
  void testGetUserById() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    when(userService.getUserById(userId)).thenReturn(Optional.of(user));

    // Act
    Optional<User> retrievedUser = userController.getUserById(userId);

    // Assert
    assertNotNull(retrievedUser);
    assertTrue(retrievedUser.isPresent());
    assertEquals(user, retrievedUser.get());
    verify(userService).getUserById(userId);
  }

  @Test
  void testGetAllUsers() {
    // Arrange
    List<User> users = new ArrayList<>();
    users.add(new User());
    when(userService.getAllUsers()).thenReturn(users);

    // Act
    List<User> retrievedUsers = userController.getAllUsers();

    // Assert
    assertNotNull(retrievedUsers);
    assertEquals(users, retrievedUsers);
    verify(userService).getAllUsers();
  }
}
