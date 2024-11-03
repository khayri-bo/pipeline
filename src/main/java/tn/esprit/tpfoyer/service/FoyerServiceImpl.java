package tn.esprit.tpfoyer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    private final FoyerRepository foyerRepository;

    @Override
    public List<Foyer> retrieveAllFoyers() {

        return foyerRepository.findAll();
    }

    @Override
    public Foyer retrieveFoyer(Long foyerId) {

        return foyerRepository.findById(foyerId).get();
    }

    @Override
    public Foyer addFoyer(Foyer f) {

        return foyerRepository.save(f);
    }

    @Override
    public Foyer modifyFoyer(Foyer foyer) {

        return foyerRepository.save(foyer);
    }

    @Override

    public void removeFoyer(Long foyerId) {

        foyerRepository.deleteById(foyerId);
    }

    // Service pour vérifier si la capacité du foyer permet un ajout
    public boolean checkFoyerCapacity(Long foyerId, long newOccupants) {
        Foyer foyer = retrieveFoyer(foyerId);
        if (foyer != null) {
            return foyer.checkFoyerCapacity(newOccupants);
        }
        return false; // Retourne false si le foyer n'existe pas
    }

    // Service pour mettre à jour la capacité du foyer
    public String updateFoyerCapacity(Long foyerId, long additionalCapacity) {
        Foyer foyer = retrieveFoyer(foyerId);
        if (foyer != null) {
            return foyer.updateFoyerCapacity(additionalCapacity);
        }
        return "Foyer non trouvé"; // Message d'erreur si le foyer n'existe pas
    }

    // Service pour récupérer l'historique des modifications de capacité
    public List<String> getCapacityChangeHistory(Long foyerId) {
        Foyer foyer = retrieveFoyer(foyerId);
        if (foyer != null) {
            return foyer.getCapacityChangeHistory();
        }
        return List.of(); // Retourne une liste vide si le foyer n'existe pas
    }




}
