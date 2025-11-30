package dcc.filiere_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFiliereDTO {
    private Integer idFiliere;
    private String code;
    private String libelle;

}
