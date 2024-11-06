package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant1;

    @BeforeEach
    void setUp() {
        // Création de quelques étudiants pour les tests
        etudiant1 = new Etudiant(1L, "John", "Doe", 123456789, new Date());
    }

    @Test
    void searchEtudiants() {
        // Arrange
        String nom = "Doe";
        String prenom = "John";
        Date dateNaissance = etudiant1.getDateNaissance();

        // Mock de la méthode de recherche
        when(etudiantRepository.findByNomEtudiantAndPrenomEtudiantAndDateNaissance(nom, prenom, dateNaissance))
                .thenReturn(Arrays.asList(etudiant1));

        // Act
        List<Etudiant> result = etudiantService.searchEtudiants(nom, prenom, dateNaissance);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(etudiant1, result.get(0));
    }

    @Test
    void countReservationsByEtudiant() {
        // Arrange
        Long etudiantId = 1L;
        etudiant1.setReservations(Set.of(new Reservation()));  // Mock de réservations
        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(etudiant1));

        // Act
        int count = etudiantService.countReservationsByEtudiant(etudiantId);

        // Assert
        assertEquals(1, count);  // Il y a une réservation dans l'étudiant1
    }
}
