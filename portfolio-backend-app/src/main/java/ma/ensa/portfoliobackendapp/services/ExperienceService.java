package ma.ensa.portfoliobackendapp.services;

import ma.ensa.portfoliobackendapp.entities.Education;
import ma.ensa.portfoliobackendapp.entities.Experience;
import ma.ensa.portfoliobackendapp.repositories.ExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    private ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public Experience save(Experience experience){
        return experienceRepository.save(experience);
    }

    public List<Experience> saveAll(List<Experience> experiences){
        return experienceRepository.saveAll(experiences);
    }
}
