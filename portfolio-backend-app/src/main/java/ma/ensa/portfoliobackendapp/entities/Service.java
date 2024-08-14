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
@Schema(description = "Représente un service offert dans le portfolio")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du service", example = "1")
    private Long id;

    @Schema(description = "Titre du service", example = "Conception de sites web")
    private String title;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Description du service", example = "Création et design de sites web personnalisés")
    private String description;
}
