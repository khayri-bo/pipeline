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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChambreServiceMockitoTest {

    @InjectMocks
    ChambreServiceImpl chambreService;

    @Mock
    ChambreRepository chambreRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRecupererChambresSelonTypWithMockito() {
        // Arrange
        TypeChambre typeChambre = TypeChambre.SIMPLE;
        List<Chambre> mockedChambres = List.of(new Chambre(), new Chambre());
        when(chambreRepository.findAllByTypeC(typeChambre)).thenReturn(mockedChambres);

        // Act
        List<Chambre> result = chambreService.recupererChambresSelonTyp(typeChambre);

        // Assert
        assertEquals(mockedChambres, result);
        verify(chambreRepository, times(1)).findAllByTypeC(typeChambre);
    }
}
