package dcc.etudiant_service.service;


import dcc.etudiant_service.DTO.Filiere;
import dcc.etudiant_service.DTO.RequestEtudiantDTO;
import dcc.etudiant_service.DTO.ResponseEtudiantDTO;

import java.util.List;

public interface EtudiantService {
    public ResponseEtudiantDTO addEtudiant(RequestEtudiantDTO requestEtudiantDTO);
    public List<ResponseEtudiantDTO> getAllEtudiants();
    public ResponseEtudiantDTO getEtudiantById(Integer id_etudiant);
    public void deleteEtudiant(Integer id_etudiant);
    public ResponseEtudiantDTO updateEtudiant(Integer id_etudiant, RequestEtudiantDTO requestEtudiantDTO);

}
