package ma.ensa.portfoliobackendapp.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkDTO {
    private Long id;
    private String title;
    private String description;
    private String link;
    private byte[] image;
}
