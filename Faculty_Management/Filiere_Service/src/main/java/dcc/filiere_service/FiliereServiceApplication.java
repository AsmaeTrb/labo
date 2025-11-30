package dcc.filiere_service;

import dcc.filiere_service.configuration.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeys.class)
public class FiliereServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiliereServiceApplication.class, args);
    }

}
