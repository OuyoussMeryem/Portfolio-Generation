package ma.ensa.portfoliobackendapp.repositories;

import ma.ensa.portfoliobackendapp.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
}
