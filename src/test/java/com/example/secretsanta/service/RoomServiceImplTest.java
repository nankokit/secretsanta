package com.example.secretsanta.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.secretsanta.cache.EntityCache;
import com.example.secretsanta.model.Room;
import com.example.secretsanta.repository.RoomRepository;
import com.example.secretsanta.service.impl.RoomServiceImpl;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RoomServiceImplTest {

  @Mock private RoomRepository roomRepository;

  @Mock private EntityManager entityManager;

  @Mock private EntityCache<Room> roomCache;

  @InjectMocks private RoomServiceImpl roomService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createRoom_ShouldPutRoomInCacheAndReturnCreatedRoom() {
    // Arrange
    Room room = new Room();
    room.setId(1L);

    // Act
    when(roomRepository.save(room)).thenReturn(room);
    Room createdRoom = roomService.createRoom(room);

    // Assert
    assertEquals(room, createdRoom);
    verify(roomCache).put(room.getId(), room);
    verify(roomRepository).save(room);
    verify(entityManager).refresh(room);
  }

  @Test
  void getRoomById_RoomInCache_ShouldReturnRoomFromCache() {
    // Arrange
    Long roomId = 1L;
    Room room = new Room();
    room.setId(roomId);

    // Act
    when(roomCache.get(roomId)).thenReturn(Optional.of(room));
    Optional<Room> retrievedRoom = roomService.getRoomById(roomId);

    // Assert
    verify(roomCache).get(roomId);
    verify(roomRepository, never()).findById(roomId);
    assertEquals(room, retrievedRoom.orElse(null));
  }

  @Test
  void getRoomById_RoomNotInCache_ShouldReturnRoomFromRepositoryAndPutInCache() {
    // Arrange
    Long roomId = 1L;
    Room room = new Room();
    room.setId(roomId);

    // Act
    when(roomCache.get(roomId)).thenReturn(Optional.empty());
    when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
    Optional<Room> retrievedRoom = roomService.getRoomById(roomId);

    // Assert
    assertEquals(room, retrievedRoom.orElse(null));
    verify(roomCache).get(roomId);
    verify(roomRepository).findById(roomId);
    verify(roomCache).put(roomId, room);
  }

  @Test
  void getAllRooms_ShouldReturnAllRooms() {
    // Arrange
    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room());
    rooms.add(new Room());

    // Act
    when(roomRepository.findAll()).thenReturn(rooms);
    List<Room> retrievedRooms = roomService.getAllRooms();

    // Assert
    verify(roomRepository).findAll();
    assertEquals(rooms, retrievedRooms);
  }

  @Test
  void updateRoom_ShouldReturnUpdatedRoom() {
    // Arrange
    Long roomId = 1L;
    Room updatedRoom = new Room();
    updatedRoom.setId(roomId);

    // Act
    when(roomRepository.save(any(Room.class))).thenReturn(updatedRoom);
    Room savedRoom = roomService.updateRoom(roomId, updatedRoom);

    // Assert
    verify(roomRepository).save(updatedRoom);
    assertEquals(updatedRoom, savedRoom);
  }

  @Test
  void deleteRoom_ShouldCallRepositoryDeleteById() {
    // Arrange
    Long roomId = 1L;

    // Act
    roomService.deleteRoom(roomId);

    // Assert
    verify(roomRepository).deleteById(roomId);
  }

  @Test
  void findRoomsByUser_ShouldReturnRoomsForUser() {
    // Arrange
    Long userId = 1L;
    List<Room> rooms = new ArrayList<>();
    rooms.add(new Room());
    rooms.add(new Room());

    // Act
    when(roomRepository.findRoomsByUser(userId)).thenReturn(rooms);
    List<Room> retrievedRooms = roomService.findRoomsByUser(userId);

    // Assert
    verify(roomRepository).findRoomsByUser(userId);
    assertEquals(rooms, retrievedRooms);
  }
}