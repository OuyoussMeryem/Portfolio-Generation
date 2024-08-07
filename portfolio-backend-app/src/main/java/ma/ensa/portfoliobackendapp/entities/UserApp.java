package ma.ensa.portfoliobackendapp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private String domain;
    private String email;
    private String telephone;
    private String facebookLink;
    private String twitterLink;
    private String linkedinLink;
    private String instagramLink;

    @Lob
    private byte[] cvPdf;
}
