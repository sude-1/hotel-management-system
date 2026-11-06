package com.hotel.reservation_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.reservation_service.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}