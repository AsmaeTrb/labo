package dcc.etudiant_service;

import dcc.etudiant_service.configuration.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeys.class)
@EnableFeignClients
public class EtudiantServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EtudiantServiceApplication.class, args);
    }

}
