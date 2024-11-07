package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface IReservationService {

    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(Long reservationId); // Change to Long
    Reservation addReservation(Reservation r);
    void removeReservation(Long reservationId); // Change to Long
    Reservation modifyReservation(Reservation reservation);

    List<Reservation> trouverResSelonDateEtStatus(LocalDate d, boolean b);

    List<Reservation> retrieveReservationsByStatus(boolean estValide);

    List<Reservation> triReservationsByDate();

    List<Reservation> retrieveReservationsInDateRange(LocalDate startDate, LocalDate endDate);
}
