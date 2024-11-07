package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> { // Change to Long

    List<Reservation> findAllByAnneeUniversitaireBeforeAndEstValide(LocalDate d, boolean b);

    List<Reservation> findAllByEstValide(boolean estValide);

    @Query("SELECT r FROM Reservation r ORDER BY r.anneeUniversitaire ASC")
    List<Reservation> findReservationsSortedByAnneeUniversitaire();

    List<Reservation> findAllByAnneeUniversitaireBetween(LocalDate startDate, LocalDate endDate);
}
