package dcc.etudiant_service.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
public class ResponseEtudiantDTO {
    private Integer idEtudiant;
    private String nom;
    private String prenom;
    private String cne;
    private Integer idFiliere;
    private Filiere filiere;
}
