package ma.ensa.portfoliobackendapp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String educationYear;
    @Column(columnDefinition = "TEXT")
    private String description;
}
