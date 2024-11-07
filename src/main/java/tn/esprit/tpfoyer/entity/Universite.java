package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Universite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idUniversite;

    String nomUniversite;
    String adresse;
    String location;

    @OneToOne(cascade = CascadeType.ALL)
    Foyer foyer;

    int nombreEtudiants;

    @OneToMany(mappedBy = "universite", cascade = CascadeType.ALL)
    List<Etudiant> etudiants;
}
