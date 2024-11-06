package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    EtudiantRepository etudiantRepository;

    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant retrieveEtudiant(Long etudiantId) {
        return etudiantRepository.findById(etudiantId).get();
    }

    public Etudiant addEtudiant(Etudiant c) {
        return etudiantRepository.save(c);
    }

    public Etudiant modifyEtudiant(Etudiant c) {
        return etudiantRepository.save(c);
    }

    public void removeEtudiant(Long etudiantId) {
        etudiantRepository.deleteById(etudiantId);
    }

    public Etudiant recupererEtudiantParCin(long cin) {
        return etudiantRepository.findEtudiantByCinEtudiant(cin);
    }

    // Service avancé : Recherche d'étudiants par nom, prénom et date de naissance
    public List<Etudiant> searchEtudiants(String nom, String prenom, Date dateNaissance) {
        return etudiantRepository.findByNomEtudiantAndPrenomEtudiantAndDateNaissance(nom, prenom, dateNaissance);
    }

    // Service avancé : Calcul du nombre de réservations par étudiant
    public int countReservationsByEtudiant(Long etudiantId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
        if (etudiant != null) {
            return etudiant.getReservations().size();
        }
        return 0;
    }
}
