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

    // Test pour la méthode findByNomUniversite()
    @Test
    public void testFindByNomUniversite() {
        // Préparation des données (Arrange)
        Universite u1 = new Universite();
        u1.setNomUniversite("Université de Paris");

        Universite u2 = new Universite();
        u2.setNomUniversite("Université Paris-Saclay");

        List<Universite> universites = Arrays.asList(u1, u2);

        // Simulation du comportement de la méthode findByNomUniversite dans le repository
        when(universiteRepository.findByNomUniversiteContainingIgnoreCase("Paris")).thenReturn(universites);

        // Exécution de la méthode (Act)
        List<Universite> result = universiteService.findByNomUniversite("Paris");

        // Vérification des résultats (Assert)
        assertEquals(2, result.size());  // Vérifie qu'il y a bien 2 universités contenant "Paris" dans leur nom
        assertEquals("Université de Paris", result.get(0).getNomUniversite());  // Vérifie que la première université est "Université de Paris"
        assertEquals("Université Paris-Saclay", result.get(1).getNomUniversite());  // Vérifie que la seconde université est "Université Paris-Saclay"
    }

    // Test pour la méthode countUniversites()
    @Test
    public void testCountUniversites() {
        // Préparation des données (Arrange)
        when(universiteRepository.count()).thenReturn(5L);  // Simule qu'il y a 5 universités en base

        // Exécution de la méthode (Act)
        long total = universiteService.countUniversites();

        // Vérification des résultats (Assert)
        assertEquals(5L, total);  // Vérifie que le total est bien 5
    }

    // Autres tests...
}
