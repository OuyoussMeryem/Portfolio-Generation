package ma.ensa.portfoliobackendapp.entities;

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
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private String brand;
    private String backgroundColor;
    private String textColor;
    private String decorationColor;
    @Column(columnDefinition = "TEXT")
    private String descriptionGlobal;

    @OneToOne(cascade = CascadeType.ALL)
    private UserApp user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Skill> skillList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Experience> experienceList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Education> educationList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Service> serviceList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Work> workList;

    private String keycloakUserId;
}
