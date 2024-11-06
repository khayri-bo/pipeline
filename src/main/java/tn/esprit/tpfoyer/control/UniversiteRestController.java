package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {

    private final IUniversiteService universiteService;

    // Récupérer toutes les universités
    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversites();
    }

    // Récupérer une université par son ID
    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long uId) {
        return universiteService.retrieveUniversite(uId);
    }

    // Ajouter une nouvelle université
    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        return universiteService.addUniversite(u);
    }

    // Supprimer une université par son ID
    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long uId) {
        universiteService.removeUniversite(uId);
    }

    // Modifier une université existante
    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        return universiteService.modifyUniversite(u);
    }

    // Compter le nombre total d'universités
    @GetMapping("/total-universites")
    public long totalUniversites() {
        return universiteService.calculateTotalUniversites();
    }

    // Récupérer les universités par emplacement
    @GetMapping("/retrieve-universites-by-location/{location}")
    public List<Universite> getUniversitesByLocation(@PathVariable("location") String location) {
        return universiteService.findByLocation(location);
    }

    // Récupérer les universités par nom
    @GetMapping("/retrieve-universites-by-name/{nomUniversite}")
    public List<Universite> getUniversitesByName(@PathVariable("nomUniversite") String nomUniversite) {
        return universiteService.findByNomUniversite(nomUniversite);
    }
}
