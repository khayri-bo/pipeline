package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.Comparator;
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
    public Reservation retrieveReservation(Long reservationId) { // Change to Long
        return reservationRepository.findById(reservationId).orElse(null);
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
    public List<Reservation> trouverResSelonDateEtStatus(LocalDate d, boolean b) {
        return reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(d, b);
    }

    @Override
    public void removeReservation(Long reservationId) { // Change to Long
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public List<Reservation> retrieveReservationsByStatus(boolean estValide) {
        return reservationRepository.findAllByEstValide(estValide);
    }

    @Override
    public List<Reservation> retrieveReservationsInDateRange(LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findAllByAnneeUniversitaireBetween(startDate, endDate);
    }

    public List<Reservation> triReservationsByDate() {
        return reservationRepository.findAll().stream()
                .sorted(Comparator.comparing(Reservation::getAnneeUniversitaire))
                .toList();
    }
}
