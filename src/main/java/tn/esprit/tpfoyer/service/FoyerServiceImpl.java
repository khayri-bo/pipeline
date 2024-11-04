package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.List;

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
        return foyerRepository.findById(foyerId)
                .orElseThrow(() -> new IllegalArgumentException("Foyer non trouvé avec l'ID : " + foyerId));
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
    @Override
    public boolean checkFoyerCapacity(Long foyerId, long newOccupants) {
        Foyer foyer = retrieveFoyer(foyerId);
        return foyer.checkFoyerCapacity(newOccupants);
    }

    // Service pour mettre à jour la capacité du foyer
    @Override
    public String updateFoyerCapacity(Long foyerId, long additionalCapacity) {
        if (additionalCapacity < 0) {
            return "La capacité additionnelle ne peut pas être négative";
        }
        Foyer foyer = retrieveFoyer(foyerId);
        return foyer.updateFoyerCapacity(additionalCapacity);
    }

    // Service pour récupérer l'historique des modifications de capacité
    @Override
    public List<String> getCapacityChangeHistory(Long foyerId) {
        Foyer foyer = retrieveFoyer(foyerId);
        return foyer.getCapacityChangeHistory();
    }
}
