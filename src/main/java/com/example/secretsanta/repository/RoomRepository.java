package com.example.secretsanta.repository;

import com.example.secretsanta.model.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  @Query("SELECT r FROM Room r JOIN r.users u WHERE u.id = :user")
  List<Room> findRoomsByUser(@Param("user") Long user);
}
