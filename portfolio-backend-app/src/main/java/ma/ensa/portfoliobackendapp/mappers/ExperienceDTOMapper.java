package ma.ensa.portfoliobackendapp.mappers;


import ma.ensa.portfoliobackendapp.dtos.ExperienceDTO;
import ma.ensa.portfoliobackendapp.entities.Experience;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExperienceDTOMapper {
    ExperienceDTO entityToDto(Experience experience);
    Experience dtoToEntity(ExperienceDTO experienceDTO);
    List<ExperienceDTO> entityToDto(List<Experience> experiences);
    List<Experience> dtoToEntity(List<ExperienceDTO> experienceDTOS);
}
