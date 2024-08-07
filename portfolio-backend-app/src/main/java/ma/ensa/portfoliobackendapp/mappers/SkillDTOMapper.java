package ma.ensa.portfoliobackendapp.mappers;


import ma.ensa.portfoliobackendapp.dtos.SkillDTO;
import ma.ensa.portfoliobackendapp.entities.Skill;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillDTOMapper {

    SkillDTO entityToDto(Skill skill);
    Skill dtoToEntity(SkillDTO skillDTO);
    List<SkillDTO> entityToDto(List<Skill> skills);
    List<Skill> dtoToEntity(List<SkillDTO> skillDTOS);
}
