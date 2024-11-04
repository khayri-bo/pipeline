package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoyerServiceImplTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    private Foyer foyer1;
    private Foyer foyer2;

    @BeforeEach
    void setUp() {
        foyer1 = new Foyer();
        foyer1.setIdFoyer(1L);
        foyer1.setNomFoyer("Foyer 1");
        foyer1.setCapaciteFoyer(100);

        foyer2 = new Foyer();
        foyer2.setIdFoyer(2L);
        foyer2.setNomFoyer("Foyer 2");
        foyer2.setCapaciteFoyer(200);
    }

    @Test
    void retrieveAllFoyers() {
        // Arrange
        when(foyerRepository.findAll()).thenReturn(Arrays.asList(foyer1, foyer2));

        // Act
        List<Foyer> result = foyerService.retrieveAllFoyers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(foyer1, result.get(0));
        assertEquals(foyer2, result.get(1));
    }

    @Test
    void retrieveFoyer() {
        // Arrange
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer1));

        // Act
        Foyer result = foyerService.retrieveFoyer(1L);

        // Assert
        assertNotNull(result);
        assertEquals(foyer1, result);
    }

    @Test
    void addFoyer() {
        // Arrange
        when(foyerRepository.save(foyer1)).thenReturn(foyer1);

        // Act
        Foyer result = foyerService.addFoyer(foyer1);

        // Assert
        assertNotNull(result);
        assertEquals(foyer1, result);
    }

    @Test
    void modifyFoyer() {
        // Arrange
        when(foyerRepository.save(foyer1)).thenReturn(foyer1);

        // Act
        Foyer result = foyerService.modifyFoyer(foyer1);

        // Assert
        assertNotNull(result);
        assertEquals(foyer1, result);
    }

    @Test
    void removeFoyer() {
        // Arrange
        doNothing().when(foyerRepository).deleteById(1L);

        // Act
        foyerService.removeFoyer(1L);

        // Assert
        verify(foyerRepository, times(1)).deleteById(1L);
    }

    @Test
    void checkFoyerCapacity() {
        // Act
        boolean result = foyer1.checkFoyerCapacity(50);

        // Assert
        assertTrue(result);

        result = foyer1.checkFoyerCapacity(150);
        assertFalse(result);
    }

    @Test
    void updateFoyerCapacity() {
        // Act
        String updateMessage = foyer1.updateFoyerCapacity(50);

        // Assert
        assertEquals("La capacité du foyer a été mise à jour à 150", updateMessage);
        assertEquals(150, foyer1.getCapaciteFoyer());
        assertEquals(1, foyer1.getCapacityChangeHistory().size());
    }

    @Test
    void capacityChangeHistoryTracking() {
        // Act
        foyer1.updateFoyerCapacity(50); // Mise à jour de la capacité
        foyer1.updateFoyerCapacity(-20); // Une autre mise à jour

        // Assert
        assertEquals(2, foyer1.getCapacityChangeHistory().size());
        assertEquals(130, foyer1.getCapaciteFoyer());
    }

    @Test
    void handleExceedingCapacity() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            foyer1.updateFoyerCapacity(1000); // Essai de dépasser la capacité
        });

        assertEquals("La capacité demandée dépasse la limite autorisée", exception.getMessage());
    }
}
