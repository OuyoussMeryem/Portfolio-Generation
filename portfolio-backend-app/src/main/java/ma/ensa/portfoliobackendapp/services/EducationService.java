package ma.ensa.portfoliobackendapp.services;

import ma.ensa.portfoliobackendapp.entities.Education;
import ma.ensa.portfoliobackendapp.repositories.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public Education save(Education education){
        return educationRepository.save(education);
    }

    public List<Education> saveAll(List<Education> educations){
        return educationRepository.saveAll(educations);
    }
}
