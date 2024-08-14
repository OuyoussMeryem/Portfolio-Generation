package ma.ensa.portfoliobackendapp.controllers;

import ma.ensa.portfoliobackendapp.entities.Portfolio;
import ma.ensa.portfoliobackendapp.services.PortfolioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/generate")
public class UserPortfolioController {

    private final PortfolioService portfolioService;

    public UserPortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Operation(summary = "Obtenir les portfolios d'un utilisateur",
            description = "Récupère la liste des portfolios associés à un utilisateur basé sur son identifiant Keycloak.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portfolios trouvés"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    })
    @GetMapping("/user/{userId}/portfolios")
    public List<Portfolio> getPortfoliosByUser(
            @Parameter(description = "Identifiant de l'utilisateur Keycloak", example = "keycloak-12345")
            @PathVariable String userId) {
        return portfolioService.findAllByKeycloakUserId(userId);
    }

    @Operation(summary = "Obtenir un portfolio par ID",
            description = "Récupère un portfolio basé sur son identifiant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portfolio trouvé"),
            @ApiResponse(responseCode = "404", description = "Portfolio non trouvé")
    })
    @GetMapping("/portfolio/{portfolioId}")
    public Portfolio getPortfoliosById(
            @Parameter(description = "Identifiant du portfolio", example = "1")
            @PathVariable Long portfolioId) {
        return portfolioService.getPortfolioById(portfolioId);
    }

    @Operation(summary = "Obtenir tous les portfolios",
            description = "Récupère la liste de tous les portfolios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portfolios trouvés"),
            @ApiResponse(responseCode = "204", description = "Aucun portfolio trouvé")
    })
    @GetMapping("/portfolios")
    public List<Portfolio> getAllPortfolios() {
        return portfolioService.getAllPortfolio();
    }
}
