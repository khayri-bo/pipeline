package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

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
        when(foyerRepository.findAll()).thenReturn(Arrays.asList(foyer1, foyer2));
        List<Foyer> result = foyerService.retrieveAllFoyers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(foyer1, result.get(0));
        assertEquals(foyer2, result.get(1));
    }

    @Test
    void retrieveFoyerByIdSuccess() {
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer1));
        Foyer result = foyerService.retrieveFoyer(1L);

        assertNotNull(result);
        assertEquals(foyer1, result);
    }

    @Test
    void retrieveFoyerByIdNotFound() {
        when(foyerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            foyerService.retrieveFoyer(1L);
        });

        assertEquals("Foyer non trouvé avec l'ID : 1", exception.getMessage());
    }

    @Test
    void addFoyer() {
        when(foyerRepository.save(foyer1)).thenReturn(foyer1);
        Foyer result = foyerService.addFoyer(foyer1);

        assertNotNull(result);
        assertEquals(foyer1, result);
    }

    @Test
    void modifyFoyer() {
        when(foyerRepository.save(foyer1)).thenReturn(foyer1);
        Foyer result = foyerService.modifyFoyer(foyer1);

        assertNotNull(result);
        assertEquals(foyer1, result);
    }

    @Test
    void removeFoyer() {
        doNothing().when(foyerRepository).deleteById(1L);
        foyerService.removeFoyer(1L);

        verify(foyerRepository, times(1)).deleteById(1L);
    }

    @Test
    void checkFoyerCapacityWithinLimit() {
        boolean result = foyer1.checkFoyerCapacity(50);
        assertTrue(result);
    }

    @Test
    void checkFoyerCapacityExceedingLimit() {
        boolean result = foyer1.checkFoyerCapacity(150);
        assertFalse(result);
    }

    @Test
    void updateFoyerCapacityValid() {
        String updateMessage = foyer1.updateFoyerCapacity(50);

        assertEquals("La capacité du foyer a été mise à jour à 150", updateMessage);
        assertEquals(150, foyer1.getCapaciteFoyer());
        assertEquals(1, foyer1.getCapacityChangeHistory().size());
        assertTrue(foyer1.getCapacityChangeHistory().get(0).contains("Ancienne capacité = 100"));
    }

    @Test
    void updateFoyerCapacityNegativeAddition() {
        String updateMessage = foyerService.updateFoyerCapacity(1L, -10);

        assertEquals("La capacité additionnelle ne peut pas être négative", updateMessage);
    }

    @Test
    void capacityChangeHistoryTracking() {
        foyer1.updateFoyerCapacity(50);
        foyer1.updateFoyerCapacity(-20);

        assertEquals(2, foyer1.getCapacityChangeHistory().size());
        assertEquals(130, foyer1.getCapaciteFoyer());
    }

    @Test
    void handleExceedingCapacity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            foyer1.updateFoyerCapacity(1000);
        });

        assertEquals("La capacité demandée dépasse la limite autorisée", exception.getMessage());
    }

    @Test
    void getCapacityChangeHistory() {
        foyer1.updateFoyerCapacity(50);
        foyer1.updateFoyerCapacity(20);

        List<String> history = foyer1.getCapacityChangeHistory();

        assertEquals(2, history.size());
        assertTrue(history.get(0).contains("Ancienne capacité = 100"));
        assertTrue(history.get(1).contains("Ancienne capacité = 150"));
    }
}
