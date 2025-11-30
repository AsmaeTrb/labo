package dcc.filiere_service.Web;


import dcc.filiere_service.DTO.RequestFiliereDTO;
import dcc.filiere_service.DTO.ResponseFiliereDTO;
import dcc.filiere_service.Service.FiliereServiceImpl;
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
                title= "Gestion des filieres",
                description= "offre tous les methodes pour gérer les filieres de l'université",
                version= "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8081/"
        )

)

@RestController
@RequestMapping("/v1/filieres")
public class ApiRestfull {
    private final FiliereServiceImpl filiereService;

    public ApiRestfull(FiliereServiceImpl filiereService) {
        this.filiereService = filiereService;
    }

    @Operation(
            summary = "Ajouter une filière",
            description = "Permet d’ajouter une nouvelle filière dans la base de données.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestFiliereDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filière ajoutée avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Erreur côté client"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @PostMapping
    public ResponseEntity<ResponseFiliereDTO> add(@RequestBody RequestFiliereDTO requestFiliereDTO) {
        ResponseFiliereDTO responseFiliereDTO = filiereService.addFiliere(requestFiliereDTO);
        return ResponseEntity.ok(responseFiliereDTO);
    }

    @Operation(
            summary = "Afficher toutes les filières",
            description = "Retourne la liste complète des filières enregistrées.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                            content = @Content(mediaType = "application/json",
                                  array = @ArraySchema(schema =@Schema(implementation = ResponseFiliereDTO.class)))
                    ),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @GetMapping
    public ResponseEntity<List<ResponseFiliereDTO>> getAll() {
        List<ResponseFiliereDTO> responseFiliereDTOs = filiereService.getAllFilieres();
        return ResponseEntity.ok(responseFiliereDTOs);
    }

    @Operation(
            summary = "Récupérer une filière par ID",
            parameters = @Parameter(name = "id", required = true),
            description = "Retourne les informations d’une filière spécifique selon son identifiant.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filière trouvée avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDTO.class))),
                    @ApiResponse(responseCode = "4xx", description = "Filière introuvable"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur côté serveur")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseFiliereDTO> getById(@PathVariable("id") Integer id) {
        ResponseFiliereDTO responseFiliereDTO = filiereService.getFiliereById(id);
        return ResponseEntity.ok(responseFiliereDTO);
    }

    @Operation(
            summary = "Supprimer une filière",
            description = "Supprime une filière de la base de données selon son identifiant.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filière supprimée avec succès"),
                    @ApiResponse(responseCode = "404", description = "Filière introuvable"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        filiereService.deleteFiliere(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Mettre à jour une filière",
            description = "Permet de modifier les informations d’une filière existante.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestFiliereDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filière mise à jour avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseFiliereDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Filière introuvable"),
                    @ApiResponse(responseCode = "500", description = "Erreur côté serveur")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseFiliereDTO> update(@PathVariable int id,@RequestBody RequestFiliereDTO requestFiliereDTO) {
        ResponseFiliereDTO responseFiliereDTO = filiereService.updateFiliere(id, requestFiliereDTO);
        return ResponseEntity.ok(responseFiliereDTO);
    }
}
