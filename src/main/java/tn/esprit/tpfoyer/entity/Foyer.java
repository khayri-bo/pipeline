package tn.esprit.tpfoyer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Foyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idFoyer;

    String nomFoyer;
    long capaciteFoyer;

    @OneToOne(mappedBy = "foyer")
    @ToString.Exclude
    @JsonIgnore
    Universite universite;

    @OneToMany(mappedBy = "foyer")
            @JsonIgnore
            @ToString.Exclude
    Set<Bloc> blocs;

    // Historique des changements de capacité
    @Transient
    @Getter // Utilisation de Lombok pour le getter
    List<String> capacityChangeHistory = new ArrayList<>();

    // Service pour vérifier si la capacité du foyer permet un ajout
    public boolean checkFoyerCapacity(long newOccupants) {
        return this.capaciteFoyer >= newOccupants;
    }
    // Service pour mettre à jour la capacité du foyer
    public String updateFoyerCapacity(long additionalCapacity) {
        long oldCapacity = this.capaciteFoyer;
        this.capaciteFoyer += additionalCapacity;

        // Enregistrer le changement dans l'historique
        logCapacityChange(oldCapacity, this.capaciteFoyer);

        return "La capacité du foyer a été mise à jour à " + this.capaciteFoyer;
    }
    // Enregistrer une modification de capacité
    private void logCapacityChange(long oldCapacity, long newCapacity) {
        String log = "Modification de la capacité le " + new Date() +
                ": Ancienne capacité = " + oldCapacity +
                ", Nouvelle capacité = " + newCapacity;
        capacityChangeHistory.add(log);
    }


}


