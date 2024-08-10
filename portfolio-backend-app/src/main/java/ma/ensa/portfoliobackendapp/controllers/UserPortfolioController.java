package ma.ensa.portfoliobackendapp.controllers;

import ma.ensa.portfoliobackendapp.entities.Portfolio;
import ma.ensa.portfoliobackendapp.services.PortfolioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/generate")
public class UserPortfolioController {

    private PortfolioService portfolioService;


    public UserPortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/user/{userId}/portfolios")
    public List<Portfolio> getPortfoliosByUser(@PathVariable String userId) {
        return portfolioService.findAllByKeycloakUserId(userId);
    }

    @GetMapping("/portfolio/{portfolioId}")
    public Portfolio getPortfoliosById(@PathVariable Long portfolioId) {
        return portfolioService.getPortfolioById(portfolioId);
    }

    @GetMapping("/portfolios")
    public List<Portfolio> getPortfoliosById() {
        return portfolioService.getAllPortfolio();
    }
}
