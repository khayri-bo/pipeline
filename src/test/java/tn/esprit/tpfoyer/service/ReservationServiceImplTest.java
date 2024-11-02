package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.time.LocalDate; // Import LocalDate
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation1;
    private Reservation reservation2;
    private LocalDate dateBefore; // Change Date to LocalDate
    private LocalDate dateAfter; // Change Date to LocalDate

    @BeforeEach
    void setUp() {
        // Setting up test data
        dateBefore = LocalDate.of(2022, 1, 1); // Use LocalDate
        dateAfter = LocalDate.of(2023, 1, 1); // Use LocalDate

        reservation1 = new Reservation("1", dateBefore, true, new HashSet<>());
        reservation2 = new Reservation("2", dateAfter, false, new HashSet<>());
    }

    @Test
    void retrieveReservationsByStatus() {
        // Arrange
        boolean status = true;
        when(reservationRepository.findAllByEstValide(status)).thenReturn(Arrays.asList(reservation1));

        // Act
        List<Reservation> result = reservationService.retrieveReservationsByStatus(status);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reservation1, result.get(0));
    }

    @Test
    void triReservationsByDate() {
        // Arrange
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2); // Include both reservations
        when(reservationRepository.findAll()).thenReturn(reservations); // Mock the behavior

        // Act
        List<Reservation> sortedReservations = reservationService.triReservationsByDate();

        // Assert
        assertNotNull(sortedReservations);
        assertEquals(2, sortedReservations.size()); // Expected 2
        assertEquals(reservation1, sortedReservations.get(0)); // Oldest first
        assertEquals(reservation2, sortedReservations.get(1));
    }

    @Test
    void retrieveReservationsInDateRange() {
        // Arrange
        LocalDate startDate = LocalDate.of(2021, 1, 1); // Use LocalDate
        LocalDate endDate = LocalDate.of(2022, 12, 31); // Use LocalDate
        when(reservationRepository.findAllByAnneeUniversitaireBetween(startDate, endDate))
                .thenReturn(Arrays.asList(reservation1));

        // Act
        List<Reservation> result = reservationService.retrieveReservationsInDateRange(startDate, endDate);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reservation1, result.get(0));
    }
}
