package ma.ensa.portfoliobackendapp.services;

import ma.ensa.portfoliobackendapp.entities.UserApp;
import ma.ensa.portfoliobackendapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAppService {

    private UserRepository userRepository;

    public UserAppService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserApp save(UserApp userApp){
        return userRepository.save(userApp);
    }
}
