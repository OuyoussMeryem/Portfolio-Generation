package ma.ensa.portfoliobackendapp.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateDebut;
    private String dateFin;
    @Column(columnDefinition = "TEXT")
    private String description;
}
