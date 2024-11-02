package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.time.LocalDate; // Import LocalDate
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation retrieveReservation(String reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);  // Use orElse(null) to handle absence of reservation
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation modifyReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> trouverResSelonDateEtStatus(LocalDate d, boolean b) { // Change Date to LocalDate
        return reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(d, b);
    }

    @Override
    public void removeReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public List<Reservation> retrieveReservationsByStatus(boolean estValide) {
        return reservationRepository.findAllByEstValide(estValide);
    }



    @Override
    public List<Reservation> retrieveReservationsInDateRange(LocalDate startDate, LocalDate endDate) { // Change Date to LocalDate
        return reservationRepository.findAllByAnneeUniversitaireBetween(startDate, endDate);
    }

    public List<Reservation> triReservationsByDate() {
        List<Reservation> reservations = reservationRepository.findAll();
        // Sort reservations by date logic goes here
        return reservations;
    }

}
