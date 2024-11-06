package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChambreServiceImplTest {

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @Mock
    private ChambreRepository chambreRepository;

    private Chambre chambre;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101L);
        chambre.setTypeC(TypeChambre.SIMPLE);
    }

    @Test
    public void testRetrieveAllChambresReturnsEmptyList() {
        // Arrange
        when(chambreRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Chambre> chambres = chambreService.retrieveAllChambres();

        // Assert
        assertTrue(chambres.isEmpty());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveChambreByIdThrowsExceptionWhenNotFound() {
        // Arrange
        when(chambreRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> chambreService.retrieveChambre(999L));
        verify(chambreRepository, times(1)).findById(999L);
    }

    @Test
    public void testAddChambreWithNullValues() {
        // Arrange
        Chambre nullChambre = new Chambre();
        when(chambreRepository.save(nullChambre)).thenReturn(nullChambre);

        // Act
        Chambre savedChambre = chambreService.addChambre(nullChambre);

        // Assert
        assertNotNull(savedChambre);
        verify(chambreRepository, times(1)).save(nullChambre);
    }

    @Test
    public void testModifyChambreNotFound() {
        // Arrange
        Chambre chambreToModify = new Chambre();
        chambreToModify.setIdChambre(2L);
        chambreToModify.setNumeroChambre(102L);

        when(chambreRepository.findById(2L)).thenReturn(Optional.empty());
        when(chambreRepository.save(chambreToModify)).thenReturn(chambreToModify);

        // Act
        Chambre modifiedChambre = chambreService.modifyChambre(chambreToModify);

        // Assert
        assertNotNull(modifiedChambre);
        verify(chambreRepository, times(1)).save(chambreToModify);
    }

    @Test
    public void testRecupererChambresSelonTypReturnsEmptyList() {
        // Arrange
        TypeChambre type = TypeChambre.DOUBLE;
        when(chambreRepository.findAllByTypeC(type)).thenReturn(Collections.emptyList());

        // Act
        List<Chambre> chambres = chambreService.recupererChambresSelonTyp(type);

        // Assert
        assertTrue(chambres.isEmpty());
        verify(chambreRepository, times(1)).findAllByTypeC(type);
    }

    @Test
    public void testTrouverChambreSelonEtudiantReturnsNull() {
        // Arrange
        long cin = 123456;
        when(chambreRepository.trouverChselonEt(cin)).thenReturn(null);

        // Act
        Chambre chambreResult = chambreService.trouverchambreSelonEtudiant(cin);

        // Assert
        assertNull(chambreResult);
        verify(chambreRepository, times(1)).trouverChselonEt(cin);
    }

    @Test
    public void testRemoveChambreWhenIdNotFound() {
        // Act
        chambreService.removeChambre(999L);

        // Assert
        verify(chambreRepository, times(1)).deleteById(999L);
    }
}
