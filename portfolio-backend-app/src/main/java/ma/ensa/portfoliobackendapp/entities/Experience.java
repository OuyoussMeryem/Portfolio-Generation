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
@Schema(description = "Représente une expérience professionnelle")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de l'expérience", example = "1")
    private Long id;

    @Schema(description = "Date de début de l'expérience", example = "2024-01-01")
    private String dateDebut;

    @Schema(description = "Date de fin de l'expérience", example = "2024-12-31")
    private String dateFin;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Description de l'expérience", example = "Développeur Java chez XYZ")
    private String description;
}
