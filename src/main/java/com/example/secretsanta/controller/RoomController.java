package com.example.secretsanta.controller;

import com.example.secretsanta.aop.RequestStats;
import com.example.secretsanta.model.Room;
import com.example.secretsanta.service.RoomService;
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
import org.springframework.web.bind.annotation.RestController;

@RequestStats
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {
  private RoomService roomService;

  @PostMapping
  public ResponseEntity<Room> createRoom(@RequestBody Room room) {
    return ResponseEntity.ok(roomService.createRoom(room));
  }

  @PutMapping("/{roomId}")
  public Room updateRoom(@PathVariable Long roomId, @RequestBody Room room) {
    room.setId(roomId);
    return roomService.updateRoom(roomId, room);
  }

  @DeleteMapping("/{roomId}")
  public void deleteRoom(@PathVariable Long roomId) {
    roomService.deleteRoom(roomId);
  }

  @GetMapping("/{roomId}")
  public Optional<Room> getRoomById(@PathVariable Long roomId) {
    return roomService.getRoomById(roomId);
  }

  @GetMapping("/getAll")
  public List<Room> getAllRooms() {
    return roomService.getAllRooms();
  }

  @GetMapping("/user/{id}")
  public List<Room> getRoomsByUserId(@PathVariable("id") Long userId) {

    return roomService.findRoomsByUser(userId);
  }
}
