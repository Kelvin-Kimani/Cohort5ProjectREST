package com.cohort5projectrest.Services;

import com.cohort5projectrest.Entities.Room;
import com.cohort5projectrest.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    /*CREATE*/
    public void createRoom(Room room){
        roomRepository.save(room);
    }

    /*READ*/
    public List<Room> showRooms(){
        return roomRepository.findAll();
    }

    public Room getRoom(int roomId){
        return roomRepository.findByRoomId(roomId);
    }

    /******************                         DELETE                                          ****************/

    public void deleteRoom(int roomId){
        roomRepository.deleteById(roomId);
    }
}
