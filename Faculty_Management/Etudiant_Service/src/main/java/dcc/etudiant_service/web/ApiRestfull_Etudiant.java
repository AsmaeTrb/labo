package dcc.etudiant_service.web;
;
import dcc.etudiant_service.DTO.RequestEtudiantDTO;
import dcc.etudiant_service.DTO.ResponseEtudiantDTO;
import dcc.etudiant_service.service.EtudiantServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Étudiants",
                description = "Offre la gestion des étudiants et la communication avec le microservice Filière.",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8082/"
        )
)
@RestController
@RequestMapping("/v1/etudiants")
public class ApiRestfull_Etudiant {

    private final EtudiantServiceImpl etudiantService;

    public ApiRestfull_Etudiant(EtudiantServiceImpl etudiantService) {
        this.etudiantService = etudiantService;
    }

    @Operation(
            summary = "Ajouter un étudiant",
            description = "Ajoute un nouvel étudiant avec son identifiant de filière.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestEtudiantDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Étudiant ajouté avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Erreur côté client (données invalides)"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @PostMapping
    public ResponseEntity<ResponseEtudiantDTO> addEtudiant(@RequestBody RequestEtudiantDTO requestEtudiantDTO) {
        ResponseEtudiantDTO responseEtudiantDTO = etudiantService.addEtudiant(requestEtudiantDTO);
        return ResponseEntity.ok(responseEtudiantDTO);
    }

    @Operation(
            summary = "Lister tous les étudiants",
            description = "Récupère la liste complète des étudiants.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseEtudiantDTO.class)))),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @GetMapping
    public ResponseEntity<List<ResponseEtudiantDTO>> getAllEtudiants() {
        List<ResponseEtudiantDTO> responseEtudiantDTOS = etudiantService.getAllEtudiants();
        return ResponseEntity.ok(responseEtudiantDTOS);
    }

    @Operation(
            summary = "Récupérer un étudiant par ID",
            description = "Renvoie les informations d’un étudiant spécifique.",
            parameters = @Parameter(
                    name = "id",
                    description = "Identifiant unique de l’étudiant",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Étudiant trouvé",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Étudiant introuvable"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEtudiantDTO> getbyid(@PathVariable Integer id) {
        ResponseEtudiantDTO responseEtudiantDTO = etudiantService.getEtudiantById(id);
        return ResponseEntity.ok(responseEtudiantDTO);
    }

    @Operation(
            summary = "Mettre à jour un étudiant",
            description = "Met à jour les informations d’un étudiant existant.",
            parameters = @Parameter(
                    name = "id",
                    description = "Identifiant unique de l’étudiant à modifier",
                    required = true
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestEtudiantDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Étudiant mis à jour avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEtudiantDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Étudiant introuvable"),
                    @ApiResponse(responseCode = "400", description = "Erreur de validation des données"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseEtudiantDTO> Update(@PathVariable Integer id, @RequestBody RequestEtudiantDTO requestEtudiantDTO) {
        ResponseEtudiantDTO e = etudiantService.updateEtudiant(id, requestEtudiantDTO);
        return ResponseEntity.ok(e);
    }

    @Operation(
            summary = "Supprimer un étudiant",
            description = "Supprime un étudiant existant à partir de son id.",
            parameters = @Parameter(
                    name = "id",
                    description = "Identifiant de l’étudiant à supprimer",
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Étudiant supprimé avec succès"),
                    @ApiResponse(responseCode = "404", description = "Étudiant introuvable"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.ok().build();
    }
}
