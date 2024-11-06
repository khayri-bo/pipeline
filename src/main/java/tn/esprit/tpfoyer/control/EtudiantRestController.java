package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {

    IEtudiantService etudiantService;

    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants() {
        return etudiantService.retrieveAllEtudiants();
    }

    @GetMapping("/retrieve-etudiant-cin/{cin}")
    public Etudiant retrieveEtudiantParCin(@PathVariable("cin") Long cin) {
        return etudiantService.recupererEtudiantParCin(cin);
    }

    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long chId) {
        return etudiantService.retrieveEtudiant(chId);
    }

    // Endpoint pour ajouter un étudiant
    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant c) {
        return etudiantService.addEtudiant(c);
    }

    // Endpoint pour supprimer un étudiant
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long chId) {
        etudiantService.removeEtudiant(chId);
    }

    // Endpoint pour modifier un étudiant
    @PutMapping("/modify-etudiant")
    public Etudiant modifyEtudiant(@RequestBody Etudiant c) {
        return etudiantService.modifyEtudiant(c);
    }

    // Service avancé : Recherche par nom, prénom et date de naissance
    @GetMapping("/search")
    public List<Etudiant> searchEtudiants(@RequestParam String nom, @RequestParam String prenom, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateNaissance) {
        return etudiantService.searchEtudiants(nom, prenom, dateNaissance);
    }

    // Service avancé : Nombre de réservations par étudiant
    @GetMapping("/count-reservations/{etudiant-id}")
    public int countReservationsByEtudiant(@PathVariable("etudiant-id") Long etudiantId) {
        return etudiantService.countReservationsByEtudiant(etudiantId);
    }
}
