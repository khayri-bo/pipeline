package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate; // Use LocalDate for better date handling
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Change this if you use a different strategy
    String idReservation;

    @Column(name = "annee_universitaire") // Optional: customize column name
    LocalDate anneeUniversitaire; // Changed to LocalDate

    boolean estValide;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // Define cascade operations
    @JoinTable(
            name = "reservation_etudiant", // Join table name
            joinColumns = @JoinColumn(name = "reservation_id"), // Column for reservation
            inverseJoinColumns = @JoinColumn(name = "etudiant_id") // Column for etudiant
    )
    Set<Etudiant> etudiants;

    // You can add a field for the total price if needed
    // double totalPrice;

}
