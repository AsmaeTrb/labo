package dcc.etudiant_service.FeignClient;


import dcc.etudiant_service.DTO.Filiere;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

@FeignClient(
        name = "Filiere-Service",
        url = "http://localhost:8081/v1/filieres" // URL de  microservice filiere
)
public interface FiliereClient {
    @GetMapping("/{id}")
    @CircuitBreaker(name="Numberofcallsfiliere",fallbackMethod = "fallback")
    @Cacheable(value = "filiere-cache", key = "#id")
    //@TimeLimiter(name="Numberofcallsfiliere",fallbackMethod = "fallback")

    public Filiere getFiliereById(@PathVariable("id") Integer id);
    default   Filiere fallback(Integer id , Exception e) {
        return  null ;
    }
    @GetMapping
    @RateLimiter(name="callsAllfiliere",fallbackMethod = "fallback")
    @Bulkhead(name="callsAllfiliere",fallbackMethod = "fallback")
    public List<Filiere> getAllFilieres();
    default List<Filiere> fallback(Exception e) {
        return Collections.emptyList();
    }
}
