package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UniversiteServiceImplTest {

    @InjectMocks
    UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    // Test de la méthode findByLocation(String location)
    @Test
    public void testFindByLocation() {
        // Préparation des données (Arrange)
        Universite u1 = new Universite();
        u1.setLocation("Paris");
        u1.setNomUniversite("Université de Paris");

        Universite u2 = new Universite();
        u2.setLocation("Paris");
        u2.setNomUniversite("Université Paris-Saclay");

        List<Universite> universites = Arrays.asList(u1, u2);
        when(universiteRepository.findByLocation("Paris")).thenReturn(universites);

        // Exécution de la méthode (Act)
        List<Universite> result = universiteService.findByLocation("Paris");

        // Vérification des résultats (Assert)
        assertEquals(2, result.size());
        assertEquals("Paris", result.get(0).getLocation());
    }

    // Test de la méthode findByNomUniversite(String nomUniversite)
    @Test
    public void testFindByNomUniversite() {
        Universite u1 = new Universite();
        u1.setNomUniversite("Université de Paris");
        Universite u2 = new Universite();
        u2.setNomUniversite("Université Paris-Saclay");
        List<Universite> universites = Arrays.asList(u1, u2);
        when(universiteRepository.findByNomUniversiteContainingIgnoreCase("Paris")).thenReturn(universites);
        List<Universite> result = universiteService.findByNomUniversite("Paris");
        assertEquals(2, result.size());
        assertEquals("Université de Paris", result.get(0).getNomUniversite());
    }

    @Test
    public void testCalculateTotalUniversites() {
        when(universiteRepository.count()).thenReturn(5L);
        long total = universiteService.calculateTotalUniversites();
        assertEquals(5L, total);
    }


    @Test
    public void testAddUniversite() {
        // Préparation des données (Arrange)
        Universite universite = new Universite();
        universite.setNomUniversite("Université Nouvelle");
        when(universiteRepository.save(universite)).thenReturn(universite);
        Universite result = universiteService.addUniversite(universite);
        assertEquals("Université Nouvelle", result.getNomUniversite());
    }

    @Test
    public void testModifyUniversite() {
        Universite universite = new Universite();
        universite.setIdUniversite(1L);
        universite.setNomUniversite("Université Modifiée");
        universite.setLocation("Lyon");
        when(universiteRepository.save(universite)).thenReturn(universite);
        Universite result = universiteService.modifyUniversite(universite);
        assertEquals("Université Modifiée", result.getNomUniversite());
        assertEquals("Lyon", result.getLocation());
    }


}
