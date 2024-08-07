package ma.ensa.portfoliobackendapp.repositories;

import ma.ensa.portfoliobackendapp.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education,Long> {
}
