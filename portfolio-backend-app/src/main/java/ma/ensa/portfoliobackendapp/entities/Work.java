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
@Schema(description = "Représente un travail dans le portfolio")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du travail", example = "1")
    private Long id;

    @Schema(description = "Titre du travail", example = "Site web e-commerce")
    private String title;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Description du travail", example = "Développement d'un site web e-commerce avec intégration de paiement")
    private String description;

    @Schema(description = "Lien vers le travail", example = "https://mon-site-web.com")
    private String link;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    @Schema(description = "Image associée au travail")
    private byte[] image;
}
