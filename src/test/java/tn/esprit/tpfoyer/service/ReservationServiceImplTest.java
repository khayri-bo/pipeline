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
    private LocalDate dateBefore;
    private LocalDate dateAfter;

    @BeforeEach
    void setUp() {
        dateBefore = LocalDate.of(2022, 1, 1);
        dateAfter = LocalDate.of(2023, 1, 1);
        reservation1 = new Reservation("1", dateBefore, true, new HashSet<>());
        reservation2 = new Reservation("2", dateAfter, false, new HashSet<>());
    }

    @Test
    void retrieveReservationsByStatus() {
        boolean status = true;
        when(reservationRepository.findAllByEstValide(status)).thenReturn(Arrays.asList(reservation1));

        List<Reservation> result = reservationService.retrieveReservationsByStatus(status);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reservation1, result.get(0));
    }

    @Test
    void retrieveReservationsByStatus_NoResults() {
        boolean status = true;
        when(reservationRepository.findAllByEstValide(status)).thenReturn(Collections.emptyList());

        List<Reservation> result = reservationService.retrieveReservationsByStatus(status);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void triReservationsByDate_SortedInput() {
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));

        List<Reservation> sortedReservations = reservationService.triReservationsByDate();

        assertNotNull(sortedReservations);
        assertEquals(2, sortedReservations.size());
        assertEquals(reservation1, sortedReservations.get(0));
        assertEquals(reservation2, sortedReservations.get(1));
    }

    @Test
    void triReservationsByDate_UnsortedInput() {
        Reservation reservation3 = new Reservation("3", LocalDate.of(2024, 1, 1), true, new HashSet<>());
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation3, reservation1, reservation2));

        List<Reservation> sortedReservations = reservationService.triReservationsByDate();

        assertNotNull(sortedReservations);
        assertEquals(3, sortedReservations.size());
        assertEquals(reservation1, sortedReservations.get(0)); // Ensure reservation1 is the first
        assertEquals(reservation2, sortedReservations.get(1));
        assertEquals(reservation3, sortedReservations.get(2)); // Ensure reservation3 is last
    }

    @Test
    void retrieveReservationsInDateRange() {
        LocalDate startDate = LocalDate.of(2021, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);
        when(reservationRepository.findAllByAnneeUniversitaireBetween(startDate, endDate))
                .thenReturn(Arrays.asList(reservation1));

        List<Reservation> result = reservationService.retrieveReservationsInDateRange(startDate, endDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reservation1, result.get(0));
    }

    @Test
    void retrieveReservationsInDateRange_NoResults() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 12, 31);
        when(reservationRepository.findAllByAnneeUniversitaireBetween(startDate, endDate))
                .thenReturn(Collections.emptyList());

        List<Reservation> result = reservationService.retrieveReservationsInDateRange(startDate, endDate);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}