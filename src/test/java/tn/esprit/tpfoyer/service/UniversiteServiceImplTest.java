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
    public void testFindByNomUniversiteExactMatch() {
        // Préparation des données (Arrange)
        Universite u1 = new Universite();
        u1.setNomUniversite("Université de Paris");

        Universite u2 = new Universite();
        u2.setNomUniversite("Université Paris-Saclay");

        List<Universite> universites = Arrays.asList(u1, u2);
        when(universiteRepository.findByNomUniversiteContainingIgnoreCase("Université de Paris")).thenReturn(Arrays.asList(u1));

        // Exécution de la méthode (Act)
        List<Universite> result = universiteService.findByNomUniversite("Université de Paris");

        // Vérification des résultats (Assert)
        assertEquals(1, result.size());  // Vérifie qu'une seule université est retournée
        assertEquals("Université de Paris", result.get(0).getNomUniversite());  // Vérifie le nom de l'université retournée
    }


    // Test de la méthode calculateTotalUniversites()
    @Test
    public void testCalculateTotalUniversitesWithLargeNumber() {
        // Simuler une base de données avec un grand nombre d'universités
        when(universiteRepository.count()).thenReturn(100L);  // Simule 100 universités

        // Exécution
        long total = universiteService.calculateTotalUniversites();

        // Vérification
        assertEquals(100L, total);  // Vérifie que le total est bien 100
    }

}
