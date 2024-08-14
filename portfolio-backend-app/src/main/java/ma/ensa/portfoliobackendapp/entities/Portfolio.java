package ma.ensa.portfoliobackendapp.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Représente un portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du portfolio", example = "1")
    private Long id;

    @Schema(description = "Date de création du portfolio", example = "2024-08-10T10:00:00Z")
    private Date createdAt;

    @Schema(description = "Marque du portfolio", example = "Portfolio XYZ")
    private String brand;

    @Schema(description = "Couleur de fond du portfolio", example = "#FFFFFF")
    private String backgroundColor;

    @Schema(description = "Couleur du texte du portfolio", example = "#000000")
    private String textColor;

    @Schema(description = "Couleur de décoration du portfolio", example = "#FF5733")
    private String decorationColor;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Description globale du portfolio", example = "Portfolio présentant mes meilleurs travaux")
    private String descriptionGlobal;

    @OneToOne(cascade = CascadeType.ALL)
    @Schema(description = "Utilisateur associé au portfolio")
    private UserApp user;

    @OneToMany(cascade = CascadeType.ALL)
    @Schema(description = "Liste des compétences du portfolio")
    private List<Skill> skillList;

    @OneToMany(cascade = CascadeType.ALL)
    @Schema(description = "Liste des expériences du portfolio")
    private List<Experience> experienceList;

    @OneToMany(cascade = CascadeType.ALL)
    @Schema(description = "Liste des éducations du portfolio")
    private List<Education> educationList;

    @OneToMany(cascade = CascadeType.ALL)
    @Schema(description = "Liste des services du portfolio")
    private List<Service> serviceList;

    @OneToMany(cascade = CascadeType.ALL)
    @Schema(description = "Liste des travaux du portfolio")
    private List<Work> workList;

    @Schema(description = "Identifiant de l'utilisateur Keycloak associé au portfolio", example = "keycloak-12345")
    private String keycloakUserId;
}
