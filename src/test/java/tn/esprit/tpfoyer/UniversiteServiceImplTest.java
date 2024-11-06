package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceImplTest {

    @InjectMocks
    UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    // Test retrieveAllUniversites
    @Test
    void testRetrieveAllUniversites() {
        // Arrange
        Universite u1 = new Universite();
        Universite u2 = new Universite();
        when(universiteRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        // Act
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Assert
        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();
    }

    // Test retrieveUniversite
    @Test
    void testRetrieveUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setIdUniversite(1L);
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        // Act
        Universite result = universiteService.retrieveUniversite(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getIdUniversite());
    }

    // Test addUniversite
    @Test
    void testAddUniversite() {
        // Arrange
        Universite universite = new Universite();
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite result = universiteService.addUniversite(universite);

        // Assert
        assertNotNull(result);
        verify(universiteRepository, times(1)).save(universite);
    }

    // Test modifyUniversite
    @Test
    void testModifyUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setNomUniversite("Updated Name");
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite result = universiteService.modifyUniversite(universite);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Name", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    // Test removeUniversite
    @Test
    void testRemoveUniversite() {
        // Act
        universiteService.removeUniversite(1L);

        // Assert
        verify(universiteRepository, times(1)).deleteById(1L);
    }

    // Test findByLocation
    @Test
    void testFindByLocation() {
        // Arrange
        Universite u1 = new Universite();
        u1.setLocation("Paris");
        Universite u2 = new Universite();
        u2.setLocation("Paris");
        when(universiteRepository.findByLocation("Paris")).thenReturn(Arrays.asList(u1, u2));

        // Act
        List<Universite> result = universiteService.findByLocation("Paris");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Paris", result.get(0).getLocation());
    }

    // Test findByNomUniversite
    @Test
    void testFindByNomUniversite() {
        // Arrange
        Universite u1 = new Universite();
        u1.setNomUniversite("Université de Paris");
        when(universiteRepository.findByNomUniversiteContainingIgnoreCase("Paris"))
                .thenReturn(List.of(u1));

        // Act
        List<Universite> result = universiteService.findByNomUniversite("Paris");

        // Assert
        assertEquals(1, result.size());
        assertEquals("Université de Paris", result.get(0).getNomUniversite());
    }

    // Test calculateTotalUniversites
    @Test
    void testCalculateTotalUniversites() {
        // Arrange
        when(universiteRepository.count()).thenReturn(5L);

        // Act
        long total = universiteService.calculateTotalUniversites();

        // Assert
        assertEquals(5L, total);
    }
}
