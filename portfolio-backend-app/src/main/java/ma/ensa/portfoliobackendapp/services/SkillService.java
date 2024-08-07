package ma.ensa.portfoliobackendapp.services;

import ma.ensa.portfoliobackendapp.entities.Experience;
import ma.ensa.portfoliobackendapp.entities.Skill;
import ma.ensa.portfoliobackendapp.repositories.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill save(Skill skill){
        return skillRepository.save(skill);
    }
    public List<Skill> saveAll(List<Skill> skills){
        return skillRepository.saveAll(skills);
    }
}
