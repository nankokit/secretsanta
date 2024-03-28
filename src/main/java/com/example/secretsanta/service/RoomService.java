package com.example.secretsanta.service;

import java.util.List;

import com.example.secretsanta.model.Room;

public interface RoomService {

    public Room createRoom(Room room);

    public Room getRoomById(Long id);

    public List<Room> getAllRooms();

    public Room updateRoom(Long id, Room updatedRoom);

    public void deleteRoom(Long id);

    public List<Room> findRoomsByUser(Long userId);
}
