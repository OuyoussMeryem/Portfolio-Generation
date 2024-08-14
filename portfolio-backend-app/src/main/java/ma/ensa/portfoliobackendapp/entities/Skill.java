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
@Schema(description = "Représente une compétence du portfolio")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de la compétence", example = "1")
    private Long id;

    @Schema(description = "Titre de la compétence", example = "Java")
    private String title;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Description de la compétence", example = "Compétence en développement Java")
    private String description;
}
