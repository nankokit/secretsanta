package com.example.secretsanta.service;

import com.example.secretsanta.model.Room;
import java.util.List;
import java.util.Optional;

public interface RoomService {

  public Room createRoom(Room room);

  public Optional<Room> getRoomById(Long id);

  public List<Room> getAllRooms();

  public Room updateRoom(Long id, Room updatedRoom);

  public void deleteRoom(Long id);

  public List<Room> findRoomsByUser(Long userId);
}
