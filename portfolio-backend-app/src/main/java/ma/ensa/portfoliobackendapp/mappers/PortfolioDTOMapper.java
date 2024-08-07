package ma.ensa.portfoliobackendapp.mappers;


import ma.ensa.portfoliobackendapp.dtos.PortfolioDTO;
import ma.ensa.portfoliobackendapp.entities.Portfolio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PortfolioDTOMapper {

    PortfolioDTO entityToDto(Portfolio portfolio);
    Portfolio dtoToEntity(PortfolioDTO portfolioDTO);
    List<PortfolioDTO> entityToDto(List<Portfolio> portfolios);
    List<Portfolio> dtoToEntity(List<PortfolioDTO> portfolioDTOS);
}
