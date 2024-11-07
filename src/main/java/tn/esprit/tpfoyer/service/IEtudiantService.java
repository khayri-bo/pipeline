package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.Date;
import java.util.List;

public interface IEtudiantService {

    public List<Etudiant> retrieveAllEtudiants();
    public Etudiant retrieveEtudiant(Long etudiantId);
    public Etudiant addEtudiant(Etudiant c);
    public void removeEtudiant(Long etudiantId);
    public Etudiant modifyEtudiant(Etudiant etudiant);
    public Etudiant recupererEtudiantParCin(long cin);

    // Service avancé : Recherche d'étudiants par nom, prénom et date de naissance
    public List<Etudiant> searchEtudiants(String nom, String prenom, Date dateNaissance);

    // Service avancé : Nombre de réservations par étudiant
    public int countReservationsByEtudiant(Long etudiantId);
}
