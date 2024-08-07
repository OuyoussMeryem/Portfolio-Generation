package ma.ensa.portfoliobackendapp.services;

import ma.ensa.portfoliobackendapp.entities.Skill;
import ma.ensa.portfoliobackendapp.entities.Work;
import ma.ensa.portfoliobackendapp.repositories.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkService {

    private WorkRepository workRepository;

    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public Work save(Work work){
        return workRepository.save(work);
    }

    public List<Work> saveAll(List<Work> works){
        return workRepository.saveAll(works);
    }
}
