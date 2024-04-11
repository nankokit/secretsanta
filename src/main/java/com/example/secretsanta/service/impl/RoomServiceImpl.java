package com.example.secretsanta.service.impl;

import com.example.secretsanta.aop.Logged;
import com.example.secretsanta.cache.EntityCache;
import com.example.secretsanta.model.Room;
import com.example.secretsanta.repository.RoomRepository;
import com.example.secretsanta.service.RoomService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

  private RoomRepository roomRepository;
  private EntityManager entityManager;
  private EntityCache<Room> roomCache;

  @Logged
  @Override
  public Room createRoom(Room room) {
    roomCache.put(room.getId(), room);
    roomRepository.save(room);
    entityManager.refresh(room);
    return room;
  }

  @Logged
  @Override
  public Optional<Room> getRoomById(Long roomId) {
    Optional<Room> room = roomCache.get(roomId);
    if (!room.isPresent()) {
      room = roomRepository.findById(roomId);
      if (room.isPresent()) {
        roomCache.put(roomId, room.get());
      }
    }
    return room;
  }

  @Logged
  @Override
  public List<Room> getAllRooms() {
    return roomRepository.findAll();
  }

  @Logged
  @Override
  public Room updateRoom(Long id, Room updatedRoom) {
    updatedRoom.setId(id);
    return roomRepository.save(updatedRoom);
  }

  @Logged
  @Override
  public void deleteRoom(Long roomId) {
    roomRepository.deleteById(roomId);
  }

  @Logged
  @Override
  public List<Room> findRoomsByUser(Long userId) {
    return roomRepository.findRoomsByUser(userId);
  }
}
