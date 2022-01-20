package com.cohort5projectrest.Repositories;

import com.cohort5projectrest.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.roomId = ?1")
    Room findByRoomId(int roomId);

    @Query("SELECT COUNT(r.roomId) FROM Room r")
    int numberOfRooms();

    @Override
    void deleteById(Integer roomId);
}
