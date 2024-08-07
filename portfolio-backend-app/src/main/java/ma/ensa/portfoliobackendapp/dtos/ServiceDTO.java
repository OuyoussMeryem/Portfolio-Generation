package ma.ensa.portfoliobackendapp.dtos;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ServiceDTO {

    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
}
