package dcc.etudiant_service.service;


import dcc.etudiant_service.DTO.Filiere;
import dcc.etudiant_service.DTO.RequestEtudiantDTO;
import dcc.etudiant_service.DTO.ResponseEtudiantDTO;
import dcc.etudiant_service.FeignClient.FiliereClient;
import dcc.etudiant_service.entity.Etudiant;
import dcc.etudiant_service.mapper.EtudiantMapper;
import dcc.etudiant_service.repository.EtudiantRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EtudiantServiceImpl implements EtudiantService {
    private FiliereClient filiereRestClient;
    private EtudiantRepository etudiantRepository;
    private EtudiantMapper etudiantMapper;

    public EtudiantServiceImpl(FiliereClient filiereRestClient, EtudiantRepository etudiantRepository, EtudiantMapper etudiantMapper) {
        this.filiereRestClient = filiereRestClient;
        this.etudiantRepository = etudiantRepository;
        this.etudiantMapper = etudiantMapper;
    }

    @Override
    public ResponseEtudiantDTO addEtudiant(RequestEtudiantDTO requestEtudiantDTO) {
        // Vérifier si la filière existe avant de sauvegarder
        try {
            Filiere filiere = filiereRestClient.getFiliereById(requestEtudiantDTO.getIdFiliere());


            if (filiere == null) {
                throw new RuntimeException("Filière introuvable !");
            }

        } catch (Exception e) {
            throw new RuntimeException("Filière introuvable : id = " + requestEtudiantDTO.getIdFiliere());
        }

        // Si la filière existe, on sauvegarde l'étudiant
        Etudiant etudiant = etudiantMapper.DTO_to_Etudiant(requestEtudiantDTO);
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);
        savedEtudiant.setFiliere(filiereRestClient.getFiliereById(savedEtudiant.getIdFiliere()));
        return etudiantMapper.Etudiant_to_DTO(savedEtudiant);
    }


    @Override
    public List<ResponseEtudiantDTO> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        List<ResponseEtudiantDTO> result = new ArrayList<>();

        for (Etudiant e : etudiants) {
            //récuperer filiere de chaque etudiant
            Filiere filiere = filiereRestClient.getFiliereById(e.getIdFiliere());
            e.setFiliere(filiere);
            ResponseEtudiantDTO dto = etudiantMapper.Etudiant_to_DTO(e);
            result.add(dto);
        }

        return result;
    }


    @Override
    public ResponseEtudiantDTO getEtudiantById(Integer id_etudiant) {
        Etudiant etudiant = etudiantRepository.findById(id_etudiant).orElse(null);
        if (etudiant == null) return null;

        // si etudiant existe on récuperer filiere.
        Filiere filiere = filiereRestClient.getFiliereById(etudiant.getIdFiliere());
        etudiant.setFiliere(filiere);

        return etudiantMapper.Etudiant_to_DTO(etudiant);
    }

    @Override
    public void deleteEtudiant(Integer id_etudiant) {
        etudiantRepository.deleteById(id_etudiant);

    }

    @Override
    public ResponseEtudiantDTO updateEtudiant(Integer id_etudiant, RequestEtudiantDTO requestEtudiantDTO) {

        Etudiant etudiant = etudiantRepository.findById(id_etudiant).orElse(null);
        Etudiant nv_etudiant = etudiantMapper.DTO_to_Etudiant(requestEtudiantDTO);

        if(nv_etudiant.getNom()!= null){ etudiant.setNom(nv_etudiant.getNom());}
        if (nv_etudiant.getPrenom()!= null){ etudiant.setPrenom(nv_etudiant.getPrenom());}
        if (nv_etudiant.getCne()!= null) { etudiant.setCne(nv_etudiant.getCne());}
        if (nv_etudiant.getIdFiliere()!= null){etudiant.setIdFiliere(nv_etudiant.getIdFiliere());}

        Etudiant saved_etudiant = etudiantRepository.save(etudiant);

        return etudiantMapper.Etudiant_to_DTO(saved_etudiant);
    }

}
