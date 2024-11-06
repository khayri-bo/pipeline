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
    private Etudiant etudiant2;

    @BeforeEach
    void setUp() {
        etudiant1 = new Etudiant(1L, "John", "Doe", 123456789, new Date());
        etudiant2 = new Etudiant(2L, "Jane", "Doe", 987654321, new Date());
    }

    @Test
    void addEtudiant() {
        // Arrange
        when(etudiantRepository.save(etudiant1)).thenReturn(etudiant1);

        // Act
        Etudiant createdEtudiant = etudiantService.addEtudiant(etudiant1);

        // Assert
        assertNotNull(createdEtudiant);
        assertEquals(etudiant1, createdEtudiant);
        verify(etudiantRepository, times(1)).save(etudiant1);
    }

    @Test
    void retrieveEtudiant() {
        // Arrange
        Long etudiantId = 1L;
        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(etudiant1));

        // Act
        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(etudiantId);

        // Assert
        assertNotNull(retrievedEtudiant);
        assertEquals(etudiant1, retrievedEtudiant);
        verify(etudiantRepository, times(1)).findById(etudiantId);
    }

    @Test
    void retrieveAllEtudiants() {
        // Arrange
        List<Etudiant> etudiants = Arrays.asList(etudiant1, etudiant2);
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(etudiant1));
        assertTrue(result.contains(etudiant2));
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void modifyEtudiant() {
        // Arrange
        etudiant1.setNomEtudiant("Johnathan");
        when(etudiantRepository.save(etudiant1)).thenReturn(etudiant1);

        // Act
        Etudiant modifiedEtudiant = etudiantService.modifyEtudiant(etudiant1);

        // Assert
        assertEquals("Johnathan", modifiedEtudiant.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant1);
    }

    @Test
    void removeEtudiant() {
        // Arrange
        Long etudiantId = 1L;

        // Act
        etudiantService.removeEtudiant(etudiantId);

        // Assert
        verify(etudiantRepository, times(1)).deleteById(etudiantId);
    }

    @Test
    void searchEtudiants() {
        // Arrange
        String nom = "Doe";
        String prenom = "John";
        Date dateNaissance = etudiant1.getDateNaissance();
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
        etudiant1.setReservations(Set.of(new Reservation()));
        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(etudiant1));

        // Act
        int count = etudiantService.countReservationsByEtudiant(etudiantId);

        // Assert
        assertEquals(1, count);
    }
}