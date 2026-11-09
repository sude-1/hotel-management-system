
package com.hotel.reservation_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.reservation_service.entity.Reservation;
import com.hotel.reservation_service.payment.Payment;
import com.hotel.reservation_service.payment.PaymentFactory;
import com.hotel.reservation_service.repository.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(
            Reservation reservation, String paymentType) {

        reservation.setStatus("PENDING");

        Reservation savedReservation =
                reservationRepository.save(reservation);

        Payment payment = PaymentFactory.createPayment(paymentType);

        boolean paymentSuccessful =
                payment.processPayment(savedReservation.getTotalPrice());

        if (paymentSuccessful) {
            savedReservation.setStatus("CONFIRMED");
        }

        return reservationRepository.save(savedReservation);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Reservation not found"));
    }

    public void deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }
}
