package com.example.secretsanta.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.secretsanta.model.Room;
import com.example.secretsanta.repository.RoomRepository;
import com.example.secretsanta.service.RoomService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private EntityManager entityManager;

    @Override
    public Room createRoom(Room room) {
        roomRepository.save(room);
        entityManager.refresh(room);
        return room;
    }

    @Override
    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new NoSuchElementException("Room not found"));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room updateRoom(Long id, Room updatedRoom) {
        updatedRoom.setId(id);
        return roomRepository.save(updatedRoom);
    }

    @Override
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }
}
