package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {

    private final UniversiteRepository universiteRepository;

    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    public Universite retrieveUniversite(Long universiteId) {
        return universiteRepository.findById(universiteId).orElse(null); // Utilisation de orElse pour gérer le cas où l'université n'existe pas
    }

    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    public Universite modifyUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    public void removeUniversite(Long universiteId) {
        universiteRepository.deleteById(universiteId);
    }

    // Méthode pour calculer le nombre total d'universités
    @Override
    public long countUniversites() {
        return universiteRepository.count(); // Appel à count() de JpaRepository
    }

    // Méthode pour trouver les universités par nom
    @Override
    public List<Universite> findByNomUniversite(String nom) {
        return universiteRepository.findByNomUniversiteContainingIgnoreCase(nom); // Recherche par nom d'université
    }

    // Méthode pour mettre à jour l'adresse d'une université
    @Override
    public Universite updateAdresse(Long universiteId, String newAdresse) {
        Universite universite = universiteRepository.findById(universiteId).orElse(null);
        if (universite != null) {
            universite.setAdresse(newAdresse);
            return universiteRepository.save(universite);
        }
        return null; // Si l'université n'est pas trouvée, retourne null
    }
}
