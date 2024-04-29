package com.example.secretsanta.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.example.secretsanta.cache.EntityCache;
import com.example.secretsanta.exception.BadRequestException;
import com.example.secretsanta.exception.NotFoundException;
import com.example.secretsanta.model.User;
import com.example.secretsanta.repository.UserRepository;
import com.example.secretsanta.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceImplTest {

  @Mock private UserRepository userRepository;

  @Mock private EntityCache<User> userCache;

  @InjectMocks private UserServiceImpl userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createUser_ValidUser_ShouldReturnCreatedUser() {
    // Arrange
    User user = new User();
    user.setId(1L);
    user.setName("John");

    // Act
    when(userRepository.save(user)).thenReturn(user);
    User createdUser = userService.createUser(user);

    // Assert
    verify(userCache).put(user.getId(), user);
    verify(userRepository).save(user);
    assertEquals(user, createdUser);
  }

  @Test
  void createUser_NullName_ShouldThrowBadRequestException() {
    // Arrange
    User user = new User();
    user.setId(1L);
    user.setName(null);

    // Act & Assert
    assertThrows(BadRequestException.class, () -> userService.createUser(user));
    verifyNoInteractions(userCache);
    verifyNoInteractions(userRepository);
  }

  @Test
  void createUser_EmptyName_ShouldThrowBadRequestException() {
    // Arrange
    User user = new User();
    user.setId(1L);
    user.setName("");

    // Act & Assert
    assertThrows(BadRequestException.class, () -> userService.createUser(user));
    verifyNoInteractions(userCache);
    verifyNoInteractions(userRepository);
  }

  @Test
  void bulkCreateUser_ValidUsers_ShouldReturnSuccessMessage() {
    // Arrange
    List<User> users = new ArrayList<>();
    User user = new User();
    user.setName("Test name");
    users.add(user);
    users.add(user);

    // Act
    when(userRepository.save(user)).thenReturn(user);
    List<User> result = userService.bulkCreateUser(users);

    // Assert
    verify(userRepository, times(2)).save(user);
    assertEquals(users, result);
  }

  @Test
  void bulkCreateUser_ExceptionThrown_ShouldReturnErrorMessage() {
    // Arrange
    List<User> users = new ArrayList<>();
    users.add(new User());
    users.add(new User());

    // Act
    when(userRepository.saveAll(users)).thenThrow(new RuntimeException());

    // Assert
    assertThrows(RuntimeException.class, () -> userService.bulkCreateUser(users));
  }

  @Test
  void getUserById_UserInCache_ShouldReturnUserFromCache() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    user.setId(userId);

    // Act
    when(userCache.get(userId)).thenReturn(Optional.of(user));
    Optional<User> retrievedUser = userService.getUserById(userId);

    // Assert
    verify(userCache).get(userId);
    verify(userRepository, never()).findById(userId);
    assertEquals(user, retrievedUser.orElse(null));
  }

  @Test
  void getUserById_UserNotInCache_ShouldReturnUserFromRepositoryAndPutInCache() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    user.setId(userId);

    // Act
    when(userCache.get(userId)).thenReturn(Optional.empty());
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    Optional<User> retrievedUser = userService.getUserById(userId);

    // Assert
    assertEquals(user, retrievedUser.orElse(null));
    verify(userCache).get(userId);
    verify(userRepository).findById(userId);
    verify(userCache).put(userId, user);
  }

  @Test
  void getUserById_UserNotFound_ShouldThrowNotFoundException() {
    // Arrange
    Long userId = 1L;

    // Act & Assert
    when(userCache.get(userId)).thenReturn(Optional.empty());
    when(userRepository.findById(userId)).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> userService.getUserById(userId));
    verify(userCache).get(userId);
    verify(userRepository).findById(userId);
    verifyNoMoreInteractions(userCache);
  }

  @Test
  void getAllUsers_ShouldReturnAllUsers() {
    // Arrange
    List<User> users = new ArrayList<>();
    users.add(new User());
    users.add(new User());

    // Act
    when(userRepository.findAll()).thenReturn(users);
    List<User> retrievedUsers = userService.getAllUsers();

    // Assert
    verify(userRepository).findAll();
    assertEquals(users, retrievedUsers);
  }

  @Test
  void updateUser_UserExists_ShouldReturnUpdatedUser() {
    // Arrange
    Long userId = 1L;
    User existingUser = new User();
    existingUser.setId(userId);
    existingUser.setName("John");

    User updatedUser = new User();
    updatedUser.setId(userId);
    updatedUser.setName("Jane");

    // Act
    when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
    when(userRepository.save(updatedUser)).thenReturn(updatedUser);
    User result = userService.updateUser(userId, updatedUser);

    // Assert
    assertEquals(updatedUser, result);
    verify(userRepository).findById(userId);
    verify(userRepository).save(updatedUser);
    verify(userCache).put(userId, updatedUser);
  }

  @Test
  void updateUser_UserNotFound_ShouldThrowNotFoundException() {
    // Arrange
    Long userId = 1L;
    User updatedUser = new User();
    updatedUser.setId(userId);
    updatedUser.setName("Jane");

    // Act & Assert
    when(userRepository.findById(userId)).thenReturn(Optional.empty());
    assertThrows(NoSuchElementException.class, () -> userService.updateUser(userId, updatedUser));
    verify(userRepository).findById(userId);
    verifyNoMoreInteractions(userRepository);
    verifyNoInteractions(userCache);
  }

  @Test
  void deleteUser_UserExists_ShouldDeleteUser() {
    // Arrange
    Long userId = 1L;

    // Act
    userService.deleteUser(userId);

    // Assert
    verify(userCache).remove(userId);
    verify(userRepository).deleteById(userId);
  }
}