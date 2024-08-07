package ma.ensa.portfoliobackendapp.mappers;


import ma.ensa.portfoliobackendapp.dtos.WorkDTO;
import ma.ensa.portfoliobackendapp.entities.Work;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkDTOMapper {

    WorkDTO entityToDto(Work userApp);
    Work dtoToEntity(WorkDTO userAppDTO);
    List<WorkDTO> entityToDto(List<Work> userApps);
    List<Work> dtoToEntity(List<WorkDTO> userAppDTOS);
}
