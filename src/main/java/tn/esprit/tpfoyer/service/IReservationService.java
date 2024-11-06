package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Reservation;

import java.time.LocalDate; // Import LocalDate
import java.util.List;

public interface IReservationService {

    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(String reservationId);
    Reservation addReservation(Reservation r);
    void removeReservation(String reservationId);
    Reservation modifyReservation(Reservation reservation);

    // Here we will add later methods calling keywords and methods calling JPQL
    List<Reservation> trouverResSelonDateEtStatus(LocalDate d, boolean b); // Change Date to LocalDate

    List<Reservation> retrieveReservationsByStatus(boolean estValide);

    List<Reservation> triReservationsByDate();

    List<Reservation> retrieveReservationsInDateRange(LocalDate startDate, LocalDate endDate); // Change Date to LocalDate
}
