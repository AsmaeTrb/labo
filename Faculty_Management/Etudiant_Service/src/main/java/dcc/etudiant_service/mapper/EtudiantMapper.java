package dcc.etudiant_service.mapper;


import dcc.etudiant_service.DTO.RequestEtudiantDTO;
import dcc.etudiant_service.DTO.ResponseEtudiantDTO;
import dcc.etudiant_service.entity.Etudiant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {
    public Etudiant DTO_to_Etudiant(RequestEtudiantDTO requestEtudiantDTO) {
        Etudiant etudiant = new Etudiant();
        BeanUtils.copyProperties(requestEtudiantDTO, etudiant);
        return etudiant;
    }

    public ResponseEtudiantDTO Etudiant_to_DTO(Etudiant etudiant) {
        ResponseEtudiantDTO responseEtudiantDTO = new ResponseEtudiantDTO();
        BeanUtils.copyProperties(etudiant, responseEtudiantDTO);
        return responseEtudiantDTO;
    }
}
