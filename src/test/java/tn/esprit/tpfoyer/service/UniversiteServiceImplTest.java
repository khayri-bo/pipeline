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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    // Test pour la méthode findByNomUniversite()
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
        assertEquals("Université Paris-Saclay", result.get(1).getNomUniversite());
    }

    // Test pour la méthode countUniversites()
    @Test
    public void testCountUniversites() {
        when(universiteRepository.count()).thenReturn(5L);

        long total = universiteService.countUniversites();

        assertEquals(5L, total);
    }

    // Test pour la méthode retrieveAllUniversites()
    @Test
    public void testRetrieveAllUniversites() {
        Universite u1 = new Universite();
        u1.setNomUniversite("Université A");

        Universite u2 = new Universite();
        u2.setNomUniversite("Université B");

        when(universiteRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Universite> universites = universiteService.retrieveAllUniversites();

        assertEquals(2, universites.size());
        assertEquals("Université A", universites.get(0).getNomUniversite());
        assertEquals("Université B", universites.get(1).getNomUniversite());
    }

    // Test pour la méthode retrieveUniversite()
    @Test
    public void testRetrieveUniversite() {
        Universite u = new Universite();
        u.setNomUniversite("Université A");

        when(universiteRepository.findById(1L)).thenReturn(Optional.of(u));

        Universite universite = universiteService.retrieveUniversite(1L);

        assertNotNull(universite);
        assertEquals("Université A", universite.getNomUniversite());
    }

    // Test pour la méthode addUniversite()
    @Test
    public void testAddUniversite() {
        Universite u = new Universite();
        u.setNomUniversite("Nouvelle Université");

        when(universiteRepository.save(u)).thenReturn(u);

        Universite savedUniversite = universiteService.addUniversite(u);

        assertNotNull(savedUniversite);
        assertEquals("Nouvelle Université", savedUniversite.getNomUniversite());
    }

    // Test pour la méthode removeUniversite()
    @Test
    public void testRemoveUniversite() {
        Long uId = 1L;

        // Pas besoin de retour ici, juste vérifier que la méthode est appelée
        doNothing().when(universiteRepository).deleteById(uId);

        universiteService.removeUniversite(uId);

        verify(universiteRepository, times(1)).deleteById(uId);
    }

    // Test pour la méthode modifyUniversite()
    @Test
    public void testModifyUniversite() {
        Universite u = new Universite();
        u.setNomUniversite("Université Modifiée");

        when(universiteRepository.save(u)).thenReturn(u);

        Universite updatedUniversite = universiteService.modifyUniversite(u);

        assertNotNull(updatedUniversite);
        assertEquals("Université Modifiée", updatedUniversite.getNomUniversite());
    }

    // Test pour la méthode updateAdresse()
    @Test
    public void testUpdateAdresse() {
        Universite u = new Universite();
        u.setNomUniversite("Université Adresse");
        u.setAdresse("Ancienne Adresse");

        when(universiteRepository.findById(1L)).thenReturn(Optional.of(u));
        when(universiteRepository.save(u)).thenReturn(u);

        Universite updatedUniversite = universiteService.updateAdresse(1L, "Nouvelle Adresse");

        assertNotNull(updatedUniversite);
        assertEquals("Nouvelle Adresse", updatedUniversite.getAdresse());
    }
}
