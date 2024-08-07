package ma.ensa.portfoliobackendapp.mappers;



import ma.ensa.portfoliobackendapp.dtos.ServiceDTO;
import ma.ensa.portfoliobackendapp.entities.Service;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceDTOMapper {

    ServiceDTO entityToDto(Service service);
    Service dtoToEntity(ServiceDTO serviceDTO);
    List<ServiceDTO> entityToDto(List<Service> services);
    List<Service> dtoToEntity(List<ServiceDTO> serviceDTOS);
}
