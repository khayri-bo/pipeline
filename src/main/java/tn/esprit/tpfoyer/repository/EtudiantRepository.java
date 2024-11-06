package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.Date;
import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Etudiant findEtudiantByCinEtudiant(long cin);

    // Méthode de recherche par nom, prénom et date de naissance
    List<Etudiant> findByNomEtudiantAndPrenomEtudiantAndDateNaissance(String nom, String prenom, Date dateNaissance);
}
