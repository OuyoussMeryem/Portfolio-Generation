package ma.ensa.portfoliobackendapp.requests;

import lombok.Getter;
import lombok.Setter;
import ma.ensa.portfoliobackendapp.dtos.*;

import java.util.List;

@Setter
@Getter
public class PortfolioRequest {
    private List<SkillDTO> skills;
    private List<ExperienceDTO> experiences;
    private List<EducationDTO> educations;
    private List<ServiceDTO> services;
    private List<WorkDTO> works;


}
