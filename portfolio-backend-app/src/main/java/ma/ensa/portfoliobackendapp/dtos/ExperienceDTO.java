package ma.ensa.portfoliobackendapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDTO {
    private String dateDebut;
    private String dateFin;
    private String description;
}
