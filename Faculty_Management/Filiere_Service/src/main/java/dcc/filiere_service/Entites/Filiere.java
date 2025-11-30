package dcc.filiere_service.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Filiere {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idFiliere;
    private String code;
    private String libelle;
}
