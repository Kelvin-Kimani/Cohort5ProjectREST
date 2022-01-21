package com.cohort5projectrest.Services;

import com.cohort5projectrest.Entities.Room;
import com.cohort5projectrest.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /*  CREATE  */
    public void createRoom(Room room){
        roomRepository.save(room);
    }
}
