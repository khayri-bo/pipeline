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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        TypeChambre typeChambre = TypeChambre.SIMPLE;
        List<Chambre> mockedChambres = List.of(new Chambre(), new Chambre());
        when(chambreRepository.findAllByTypeC(typeChambre)).thenReturn(mockedChambres);

        List<Chambre> result = chambreService.recupererChambresSelonTyp(typeChambre);

        assertEquals(mockedChambres, result);
        verify(chambreRepository, times(1)).findAllByTypeC(typeChambre);
    }

    @Test
    public void testRetrieveChambreWithMockito() {
        Long id = 2L;
        Chambre chambre = new Chambre();
        chambre.setIdChambre(id);
        when(chambreRepository.findById(id)).thenReturn(Optional.of(chambre));

        Chambre result = chambreService.retrieveChambre(id);

        assertNotNull(result);
        assertEquals(id, result.getIdChambre());
        verify(chambreRepository, times(1)).findById(id);
    }

    @Test
    public void testAddChambreWithMockito() {
        Chambre chambre = new Chambre();
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre result = chambreService.addChambre(chambre);

        assertNotNull(result);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    public void testRemoveChambreWithMockito() {
        Long id = 2L;

        chambreService.removeChambre(id);

        verify(chambreRepository, times(1)).deleteById(id);
    }
}
