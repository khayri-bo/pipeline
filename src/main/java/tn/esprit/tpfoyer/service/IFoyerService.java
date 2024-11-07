package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Foyer;

import java.util.List;

public interface IFoyerService {

    public List<Foyer> retrieveAllFoyers();
    public Foyer retrieveFoyer(Long foyerId);
    public Foyer addFoyer(Foyer f);
    public void removeFoyer(Long foyerId);
    public Foyer modifyFoyer(Foyer foyer);

    // Vérifier si la capacité du foyer permet l'ajout d'occupants
    boolean checkFoyerCapacity(Long foyerId, long newOccupants);

    // Mettre à jour la capacité du foyer
    String updateFoyerCapacity(Long foyerId, long additionalCapacity);

    // Récupérer l'historique des changements de capacité
    List<String> getCapacityChangeHistory(Long foyerId);

}
