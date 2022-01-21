package com.cohort5projectrest.Repositories;

import com.cohort5projectrest.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findByRoomId(int roomId);
}
