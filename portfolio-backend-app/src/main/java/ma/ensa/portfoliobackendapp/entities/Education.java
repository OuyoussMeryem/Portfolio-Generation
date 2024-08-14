package ma.ensa.portfoliobackendapp.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Représente une éducation")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de l'éducation", example = "1")
    private Long id;

    @Schema(description = "Année de l'éducation", example = "2024")
    private String educationYear;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Description de l'éducation", example = "Diplôme en ingénierie informatique")
    private String description;
}
