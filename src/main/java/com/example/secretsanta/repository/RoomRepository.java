package com.example.secretsanta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.secretsanta.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
