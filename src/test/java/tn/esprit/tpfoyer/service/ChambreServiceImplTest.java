package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChambreServiceImplTest {

    private ChambreServiceImpl chambreService;
    private ChambreRepository chambreRepository;

    @BeforeEach
    public void setUp() {
        chambreRepository = mock(ChambreRepository.class);
        chambreService = new ChambreServiceImpl(chambreRepository);
    }

    @Test
    public void testRecupererChambresSelonTyp() {
        // Arrange
        TypeChambre type = TypeChambre.DOUBLE;
        List<Chambre> expectedChambres = List.of(new Chambre(), new Chambre()); // Mocked chambre list
        when(chambreRepository.findAllByTypeC(type)).thenReturn(expectedChambres);

        // Act
        List<Chambre> actualChambres = chambreService.recupererChambresSelonTyp(type);

        // Assert
        assertEquals(expectedChambres, actualChambres);
        verify(chambreRepository, times(1)).findAllByTypeC(type);
    }

}
