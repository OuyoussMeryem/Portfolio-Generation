package ma.ensa.portfoliobackendapp.controllers;

import ma.ensa.portfoliobackendapp.services.PortfolioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    private PortfolioService portfolioService;

    public AdminController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/admin/portfolios-summary")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Map<String, Object> getPortfoliosSummary() {
        Map<String, Object> response = new HashMap<>();
        List<Object[]> portfoliosByUser = portfolioService.getPortfoliosCountByUser();
        Long totalPortfolios = portfolioService.getTotalPortfolios();

        response.put("totalPortfolios", totalPortfolios);
        response.put("portfoliosByUser", portfoliosByUser);
        return response;
    }
}
