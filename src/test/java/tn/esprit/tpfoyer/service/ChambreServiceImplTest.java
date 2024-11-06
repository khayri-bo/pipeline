package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChambreServiceImplTest {

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
    public void testRetrieveAllChambres() {
        // Arrange
        List<Chambre> expectedChambres = List.of(chambre);
        when(chambreRepository.findAll()).thenReturn(expectedChambres);

        // Act
        List<Chambre> actualChambres = chambreService.retrieveAllChambres();

        // Assert
        assertEquals(expectedChambres, actualChambres);
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveChambreById() {
        // Arrange
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        // Act
        Chambre result = chambreService.retrieveChambre(1L);

        // Assert
        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    public void testRetrieveChambreByIdNotFound() {
        // Arrange
        when(chambreRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> chambreService.retrieveChambre(1L));
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddChambre() {
        // Arrange
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // Act
        Chambre result = chambreService.addChambre(chambre);

        // Assert
        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    public void testModifyChambre() {
        // Arrange
        chambre.setNumeroChambre(102L);
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // Act
        Chambre result = chambreService.modifyChambre(chambre);

        // Assert
        assertNotNull(result);
        assertEquals(102L, result.getNumeroChambre());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    public void testRemoveChambre() {
        // Act
        chambreService.removeChambre(1L);

        // Assert
        verify(chambreRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testRecupererChambresSelonTyp() {
        // Arrange
        TypeChambre type = TypeChambre.DOUBLE;
        List<Chambre> expectedChambres = List.of(new Chambre(), new Chambre());
        when(chambreRepository.findAllByTypeC(type)).thenReturn(expectedChambres);

        // Act
        List<Chambre> actualChambres = chambreService.recupererChambresSelonTyp(type);

        // Assert
        assertEquals(expectedChambres, actualChambres);
        verify(chambreRepository, times(1)).findAllByTypeC(type);
    }

    @Test
    public void testTrouverChambreSelonEtudiant() {
        // Arrange
        long cin = 123456;
        when(chambreRepository.trouverChselonEt(cin)).thenReturn(chambre);

        // Act
        Chambre result = chambreService.trouverchambreSelonEtudiant(cin);

        // Assert
        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).trouverChselonEt(cin);
    }
}
