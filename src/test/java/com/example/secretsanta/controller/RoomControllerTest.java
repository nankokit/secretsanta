package com.example.secretsanta.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.secretsanta.model.Room;
import com.example.secretsanta.service.RoomService;
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

class RoomControllerTest {
  @Mock private RoomService roomService;

  @InjectMocks private RoomController roomController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateRoom() {
    // Arrange
    Room room = new Room();
    when(roomService.createRoom(any(Room.class))).thenReturn(room);

    // Act
    ResponseEntity<Room> response = roomController.createRoom(room);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(room, response.getBody());
    verify(roomService, times(1)).createRoom(any(Room.class));
  }

  @Test
  void testUpdateRoom() {
    // Arrange
    Long roomId = 1L;
    Room room = new Room();
    room.setId(roomId);
    when(roomService.updateRoom(eq(roomId), any(Room.class))).thenReturn(room);

    // Act
    Room updatedRoom = roomController.updateRoom(roomId, room);

    // Assert
    assertNotNull(updatedRoom);
    assertEquals(room, updatedRoom);
    verify(roomService, times(1)).updateRoom(eq(roomId), any(Room.class));
  }

  @Test
  void testDeleteRoom() {
    // Arrange
    Long roomId = 1L;

    // Act
    roomController.deleteRoom(roomId);

    // Assert
    verify(roomService, times(1)).deleteRoom(eq(roomId));
  }

  @Test
  void testGetRoomById() {
    // Arrange
    Long roomId = 1L;
    Room room = new Room();
    when(roomService.getRoomById(eq(roomId))).thenReturn(Optional.of(room));

    // Act
    Optional<Room> retrievedRoom = roomController.getRoomById(roomId);

    // Assert
    assertNotNull(retrievedRoom);
    assertTrue(retrievedRoom.isPresent());
    assertEquals(room, retrievedRoom.get());
    verify(roomService, times(1)).getRoomById(eq(roomId));
  }

  @Test
  void testGetAllRooms() {
    // Arrange
    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room());
    when(roomService.getAllRooms()).thenReturn(rooms);

    // Act
    List<Room> retrievedRooms = roomController.getAllRooms();

    // Assert
    assertNotNull(retrievedRooms);
    assertEquals(rooms, retrievedRooms);
    verify(roomService, times(1)).getAllRooms();
  }

  @Test
  void testGetRoomsByUserId() {
    // Arrange
    Long userId = 1L;
    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room());
    when(roomService.findRoomsByUser(eq(userId))).thenReturn(rooms);

    // Act
    List<Room> retrievedRooms = roomController.getRoomsByUserId(userId);

    // Assert
    assertNotNull(retrievedRooms);
    assertEquals(rooms, retrievedRooms);
    verify(roomService, times(1)).findRoomsByUser(eq(userId));
  }
}
