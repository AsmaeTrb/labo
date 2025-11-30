package dcc.etudiant_service.FeignClient;


import dcc.etudiant_service.DTO.Filiere;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "Filiere-Service",
        url = "http://localhost:8081/v1/filieres" // URL de  microservice filiere
)
public interface FiliereClient {

    @GetMapping("/{id}")
    public Filiere getFiliereById(@PathVariable("id") Integer id);


}
