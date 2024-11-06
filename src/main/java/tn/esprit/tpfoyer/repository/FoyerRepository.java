package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Foyer;

import java.util.Optional;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {

    // Trouver un foyer par son nom
    Optional<Foyer> findByNomFoyer(String nomFoyer);

    // Compter le nombre de foyers avec une capacité supérieure à une valeur donnée
    long countByCapaciteFoyerGreaterThan(long capacite);

    // Supprimer un foyer par son nom
    void deleteByNomFoyer(String nomFoyer);




}
