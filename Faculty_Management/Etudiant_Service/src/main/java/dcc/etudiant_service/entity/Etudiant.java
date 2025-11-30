package dcc.etudiant_service.entity;


import dcc.etudiant_service.DTO.Filiere;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idEtudiant;
    private String nom;
    private String prenom;
    private String cne;
    private Integer idFiliere;

    //@OneToMany(mappedBy ="filiere") n'est dans la meme DB
    @Transient //l'attribue n'est pas représenter dans la DB ----> n'est persistant.
    private Filiere filiere;
}
