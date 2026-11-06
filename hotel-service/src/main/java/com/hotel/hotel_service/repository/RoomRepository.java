package com.hotel.hotel_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.hotel_service.entity.Room;;

public interface RoomRepository extends JpaRepository<Room, Long>{

}
