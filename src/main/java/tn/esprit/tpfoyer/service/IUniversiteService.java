package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Universite;

import java.util.List;

public interface IUniversiteService {

    List<Universite> retrieveAllUniversites();
    Universite retrieveUniversite(Long universiteId);
    Universite addUniversite(Universite u);
    void removeUniversite(Long universiteId);
    Universite modifyUniversite(Universite universite);

    // Méthodes supplémentaires
    List<Universite> findByLocation(String location);
    List<Universite> findByNomUniversite(String nomUniversite);
    long calculateTotalUniversites();

    // Ici, nous ajouterons plus tard des méthodes utilisant des mots-clés et des méthodes JPQL
}
