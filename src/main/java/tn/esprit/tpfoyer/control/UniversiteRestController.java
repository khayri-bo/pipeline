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

    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversites();
    }

    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long uId) {
        return universiteService.retrieveUniversite(uId);
    }

    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        return universiteService.addUniversite(u);
    }

    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long uId) {
        universiteService.removeUniversite(uId);
    }

    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        return universiteService.modifyUniversite(u);
    }

    @GetMapping("/find-universite-by-name/{nom}")
    public List<Universite> findByNomUniversite(@PathVariable String nom) {
        return universiteService.findByNomUniversite(nom);
    }

    @PutMapping("/update-adresse/{universite-id}")
    public Universite updateAdresse(@PathVariable("universite-id") Long uId, @RequestParam String newAdresse) {
        return universiteService.updateAdresse(uId, newAdresse);
    }

    @GetMapping("/count-universites")
    public long countUniversites() {
        return universiteService.countUniversites(); // Appel à countUniversites()
    }
}
