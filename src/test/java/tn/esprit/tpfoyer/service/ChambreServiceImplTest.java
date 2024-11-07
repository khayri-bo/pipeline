package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        TypeChambre type = TypeChambre.DOUBLE;
        List<Chambre> expectedChambres = List.of(new Chambre(), new Chambre());
        when(chambreRepository.findAllByTypeC(type)).thenReturn(expectedChambres);

        List<Chambre> actualChambres = chambreService.recupererChambresSelonTyp(type);

        assertEquals(expectedChambres, actualChambres);
        verify(chambreRepository, times(1)).findAllByTypeC(type);
    }

    @Test
    public void testRetrieveChambre() {
        Long id = 1L;
        Chambre chambre = new Chambre();
        chambre.setIdChambre(id);
        when(chambreRepository.findById(id)).thenReturn(Optional.of(chambre));

        Chambre result = chambreService.retrieveChambre(id);

        assertNotNull(result);
        assertEquals(id, result.getIdChambre());
        verify(chambreRepository, times(1)).findById(id);
    }

    @Test
    public void testAddChambre() {
        Chambre chambre = new Chambre();
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre result = chambreService.addChambre(chambre);

        assertNotNull(result);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    public void testRemoveChambre() {
        Long id = 1L;

        chambreService.removeChambre(id);

        verify(chambreRepository, times(1)).deleteById(id);
    }
}
