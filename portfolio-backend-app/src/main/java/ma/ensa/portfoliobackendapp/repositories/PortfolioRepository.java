package ma.ensa.portfoliobackendapp.repositories;

import ma.ensa.portfoliobackendapp.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {

    List<Portfolio> findByKeycloakUserId(String keycloakUserId);

    @Query("SELECT p.keycloakUserId, COUNT(p) FROM Portfolio p GROUP BY p.keycloakUserId")
    List<Object[]> countPortfoliosByUser();

    @Query("SELECT COUNT(p) FROM Portfolio p")
    Long countTotalPortfolios();
}
