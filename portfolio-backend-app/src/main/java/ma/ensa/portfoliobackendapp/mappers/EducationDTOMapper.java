package ma.ensa.portfoliobackendapp.mappers;

import ma.ensa.portfoliobackendapp.dtos.EducationDTO;
import ma.ensa.portfoliobackendapp.entities.Education;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationDTOMapper {

    EducationDTO entityToDto(Education education);
    Education dtoToEntity(EducationDTO educationDTO);
    List<EducationDTO> entityToDto(List<Education> education);
    List<Education> dtoToEntity(List<EducationDTO> educationDTO);
}
