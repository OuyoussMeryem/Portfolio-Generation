package ma.ensa.portfoliobackendapp.repositories;

import ma.ensa.portfoliobackendapp.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
}
