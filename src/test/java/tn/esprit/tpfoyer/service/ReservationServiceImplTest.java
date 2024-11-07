package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.time.LocalDate;
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
        // Setting up test data
        dateBefore = LocalDate.of(2022, 1, 1);
        dateAfter = LocalDate.of(2023, 1, 1);

        reservation1 = new Reservation(1L, dateBefore, true, new HashSet<>());
        reservation2 = new Reservation(2L, dateAfter, false, new HashSet<>());
    }

    @Test
    void addReservation() {
        // Arrange
        when(reservationRepository.save(reservation1)).thenReturn(reservation1);

        // Act
        Reservation result = reservationService.addReservation(reservation1);

        // Assert
        assertNotNull(result);
        assertEquals(reservation1, result);
        verify(reservationRepository, times(1)).save(reservation1);
    }

    @Test
    void retrieveReservation() {
        // Arrange
        Long reservationId = 1L;
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation1));

        // Act
        Reservation result = reservationService.retrieveReservation(reservationId);

        // Assert
        assertNotNull(result);
        assertEquals(reservation1, result);
        verify(reservationRepository, times(1)).findById(reservationId);
    }

    @Test
    void modifyReservation() {
        // Arrange
        reservation1.setEstValide(false);
        when(reservationRepository.save(reservation1)).thenReturn(reservation1);

        // Act
        Reservation result = reservationService.modifyReservation(reservation1);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEstValide());
        verify(reservationRepository, times(1)).save(reservation1);
    }

    @Test
    void removeReservation() {
        // Arrange
        Long reservationId = 1L;

        // Act
        reservationService.removeReservation(reservationId);

        // Assert
        verify(reservationRepository, times(1)).deleteById(reservationId);
    }

    @Test
    void retrieveAllReservations() {
        // Arrange
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));

        // Act
        List<Reservation> result = reservationService.retrieveAllReservations();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(reservation1, result.get(0));
        assertEquals(reservation2, result.get(1));
        verify(reservationRepository, times(1)).findAll();
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
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));

        // Act
        List<Reservation> sortedReservations = reservationService.triReservationsByDate();

        // Assert
        assertNotNull(sortedReservations);
        assertEquals(2, sortedReservations.size());
        assertEquals(reservation1, sortedReservations.get(0));
        assertEquals(reservation2, sortedReservations.get(1));
    }

    @Test
    void retrieveReservationsInDateRange() {
        // Arrange
        LocalDate startDate = LocalDate.of(2021, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 1);
        when(reservationRepository.findAllByAnneeUniversitaireBetween(startDate, endDate)).thenReturn(Arrays.asList(reservation1, reservation2));

        // Act
        List<Reservation> result = reservationService.retrieveReservationsInDateRange(startDate, endDate);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(reservation1, result.get(0));
        assertEquals(reservation2, result.get(1));
    }
}
