package ma.ensa.portfoliobackendapp.mappers;


import ma.ensa.portfoliobackendapp.dtos.UserAppDTO;
import ma.ensa.portfoliobackendapp.entities.UserApp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAppDTOMapper {

    UserAppDTO entityToDto(UserApp userApp);
    UserApp dtoToEntity(UserAppDTO userAppDTO);
    List<UserAppDTO> entityToDto(List<UserApp> userApps);
    List<UserApp> dtoToEntity(List<UserAppDTO> userAppDTOS);
}
