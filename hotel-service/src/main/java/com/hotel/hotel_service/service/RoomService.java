package com.hotel.hotel_service.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hotel.hotel_service.entity.Room;
import com.hotel.hotel_service.repository.RoomRepository;

@Service
public class RoomService {
    
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room createRoom(Room room){
        return roomRepository.save(room);  //save room into database and return
    }

    public Room getRoomById(Long id){
        return roomRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Room not found with id: " + id

            ));
    }

    public Room updateRoom(Long id, Room updatedRoom) {
        Room existingRoom = getRoomById(id);
    
        existingRoom.setRoomNumber(updatedRoom.getRoomNumber());
        existingRoom.setRoomType(updatedRoom.getRoomType());
        existingRoom.setPricePerNight(updatedRoom.getPricePerNight());
        existingRoom.setAvailable(updatedRoom.isAvailable());
    
        return roomRepository.save(existingRoom);
    }

    public void deleteRoom(Long id){
        Room room = getRoomById(id);
        roomRepository.delete(room);
    }

}
