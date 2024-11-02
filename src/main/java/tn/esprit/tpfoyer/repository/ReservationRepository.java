package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Reservation;

import java.time.LocalDate; // Import LocalDate
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    List<Reservation> findAllByAnneeUniversitaireBeforeAndEstValide(LocalDate d, boolean b);

    List<Reservation> findAllByEstValide(boolean estValide);

    // New method for sorting by "anneeUniversitaire" in ascending order
    @Query("SELECT r FROM Reservation r ORDER BY r.anneeUniversitaire ASC")
    List<Reservation> findReservationsSortedByAnneeUniversitaire();

    // New method for retrieving reservations in a date range
    List<Reservation> findAllByAnneeUniversitaireBetween(LocalDate startDate, LocalDate endDate);
}
