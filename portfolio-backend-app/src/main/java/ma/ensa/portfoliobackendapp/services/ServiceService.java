package ma.ensa.portfoliobackendapp.services;

import ma.ensa.portfoliobackendapp.entities.Experience;
import ma.ensa.portfoliobackendapp.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public ma.ensa.portfoliobackendapp.entities.Service save(ma.ensa.portfoliobackendapp.entities.Service service){
        return serviceRepository.save(service);
    }

    public List<ma.ensa.portfoliobackendapp.entities.Service> saveAll(List<ma.ensa.portfoliobackendapp.entities.Service> services){
        return serviceRepository.saveAll(services);
    }
}
