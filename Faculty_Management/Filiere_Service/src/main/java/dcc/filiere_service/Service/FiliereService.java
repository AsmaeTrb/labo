package dcc.filiere_service.Service;



import dcc.filiere_service.DTO.RequestFiliereDTO;
import dcc.filiere_service.DTO.ResponseFiliereDTO;

import java.util.List;


public interface FiliereService {
    public ResponseFiliereDTO addFiliere(RequestFiliereDTO requestFiliereDTO);
    public List<ResponseFiliereDTO> getAllFilieres();
    public ResponseFiliereDTO getFiliereById(Integer id);
    public void deleteFiliere(Integer id);
    public ResponseFiliereDTO updateFiliere(Integer id, RequestFiliereDTO requestFiliereDTO);
}
