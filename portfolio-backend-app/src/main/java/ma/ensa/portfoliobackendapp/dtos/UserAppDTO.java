package ma.ensa.portfoliobackendapp.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAppDTO {
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
    private byte[] cvPdf;
}
