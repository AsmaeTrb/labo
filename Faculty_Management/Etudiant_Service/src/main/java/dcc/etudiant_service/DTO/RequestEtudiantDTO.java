package dcc.etudiant_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor

public class RequestEtudiantDTO {
    private String nom;
    private String prenom;
    private String cne;
    private Integer idFiliere;
}
