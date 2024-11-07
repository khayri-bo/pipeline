package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idEtudiant;

    String nomEtudiant;
    String prenomEtudiant;
    long cinEtudiant;
    Date dateNaissance;

    @ManyToOne // Indique que plusieurs étudiants peuvent appartenir à la même université
    @JoinColumn(name = "universite_id") // Nom de la colonne dans la table Etudiant
    Universite universite; // Ajout de la relation vers Universite

    @ManyToMany(mappedBy = "etudiants")
    Set<Reservation> reservations;

}
