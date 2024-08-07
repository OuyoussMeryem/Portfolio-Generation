package ma.ensa.portfoliobackendapp.repositories;

import ma.ensa.portfoliobackendapp.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserApp,Long> {
}
