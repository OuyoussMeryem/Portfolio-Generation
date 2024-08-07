package ma.ensa.portfoliobackendapp.dtos;


import lombok.*;
import ma.ensa.portfoliobackendapp.entities.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioDTO {
    private Long id;
    private Date createdAt;
    private String brand;
    private String backgroundColor;
    private String textColor;
    private String decorationColor;
    private String descriptionGlobal;
    private UserApp user;
    private List<Skill> skillList;
    private List<Experience> experienceList;
    private List<Education> educationList;
    private List<Service> serviceList;
    private List<Work> workList;
}
