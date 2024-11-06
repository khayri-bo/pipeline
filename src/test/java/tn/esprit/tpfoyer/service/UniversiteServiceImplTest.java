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
        assertEquals(2, result.size());  // Vérifie qu'il y a bien 2 universités
        assertEquals("Paris", result.get(0).getLocation());  // Vérifie que la première université est à Paris
    }

    // Test de la méthode findByNomUniversite(String nomUniversite)
    @Test
    public void testFindByNomUniversite() {
        // Préparation des données (Arrange)
        Universite u1 = new Universite();
        u1.setNomUniversite("Université de Paris");

        Universite u2 = new Universite();
        u2.setNomUniversite("Université Paris-Saclay");

        List<Universite> universites = Arrays.asList(u1, u2);
        when(universiteRepository.findByNomUniversiteContainingIgnoreCase("Paris")).thenReturn(universites);

        // Exécution de la méthode (Act)
        List<Universite> result = universiteService.findByNomUniversite("Paris");

        // Vérification des résultats (Assert)
        assertEquals(2, result.size());  // Vérifie qu'il y a bien 2 universités correspondant au nom "Paris"
        assertEquals("Université de Paris", result.get(0).getNomUniversite());  // Vérifie que la première université correspond
    }

    // Test de la méthode calculateTotalUniversites()
    @Test
    public void testCalculateTotalUniversites() {
        // Préparation des données (Arrange)
        when(universiteRepository.count()).thenReturn(5L);  // Simule qu'il y a 5 universités en base

        // Exécution de la méthode (Act)
        long total = universiteService.calculateTotalUniversites();

        // Vérification des résultats (Assert)
        assertEquals(5L, total);  // Vérifie que le total est bien 5
    }
}