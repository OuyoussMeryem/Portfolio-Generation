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
@Schema(description = "Représente un utilisateur du portfolio")
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de l'utilisateur", example = "1")
    private Long id;

    @Schema(description = "Prénom de l'utilisateur", example = "Meryem")
    private String firstName;

    @Schema(description = "Nom de famille de l'utilisateur", example = "Ouyouss")
    private String lastName;

    @Schema(description = "Pays de l'utilisateur", example = "Maroc")
    private String country;

    @Schema(description = "Domaine de l'utilisateur", example = "Développement java")
    private String domain;

    @Schema(description = "Email de l'utilisateur", example = "ouyss.meryem@gmail.com")
    private String email;

    @Schema(description = "Téléphone de l'utilisateur", example = "0651061415")
    private String telephone;

    @Schema(description = "Lien Facebook de l'utilisateur", example = "https://facebook.com/....")
    private String facebookLink;

    @Schema(description = "Lien Twitter de l'utilisateur", example = "https://twitter.com/....")
    private String twitterLink;

    @Schema(description = "Lien LinkedIn de l'utilisateur", example = "https://linkedin.com/....")
    private String linkedinLink;

    @Schema(description = "Lien Instagram de l'utilisateur", example = "https://instagram.com/....")
    private String instagramLink;

    @Lob
    @Schema(description = "CV PDF de l'utilisateur")
    private byte[] cvPdf;
}
