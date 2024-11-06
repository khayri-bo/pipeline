package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Universite;
import java.util.List;

public interface IUniversiteService {

    List<Universite> retrieveAllUniversites();
    Universite retrieveUniversite(Long universiteId);
    Universite addUniversite(Universite u);
    void removeUniversite(Long universiteId);
    Universite modifyUniversite(Universite universite);

    // Ajouter cette méthode pour rechercher des universités par leur nom
    List<Universite> findByNomUniversite(String nom);

    Universite updateAdresse(Long universiteId, String newAdresse);

    // Ajoutez cette méthode pour calculer le nombre total d'universités
    long countUniversites();
}
