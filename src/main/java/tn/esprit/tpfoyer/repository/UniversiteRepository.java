package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entity.Universite;
import java.util.List;

public interface UniversiteRepository extends JpaRepository<Universite, Long> {

    // Méthode pour rechercher par nom d'université
    List<Universite> findByNomUniversiteContainingIgnoreCase(String nom);

    // La méthode count() est déjà fournie par JpaRepository
}
