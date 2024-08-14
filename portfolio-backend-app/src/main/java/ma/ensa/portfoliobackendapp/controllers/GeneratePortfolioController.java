package ma.ensa.portfoliobackendapp.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ma.ensa.portfoliobackendapp.dtos.*;
import ma.ensa.portfoliobackendapp.entities.*;
import ma.ensa.portfoliobackendapp.requests.PortfolioRequest;
import ma.ensa.portfoliobackendapp.services.PortfolioService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/generate")
public class GeneratePortfolioController {

    private PortfolioService portfolioService;

    public GeneratePortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Operation(
            summary = "Générer un portfolio",
            description = "Crée un portfolio à partir des informations fournies et renvoie le portfolio en format ZIP."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portfolio généré avec succès", content = @Content(mediaType = "application/zip")),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping(value = "/portfolio", consumes = "multipart/form-data")
    public ResponseEntity<byte[]> generatePortfolioSeparer(
            @Parameter(description = "Identifiant de l'utilisateur Keycloak", example = "keycloak-12345")
            @RequestParam("keycloakUserId") String keycloakUserId,

            @Parameter(description = "Marque du portfolio", example = "Porftolio")
            @RequestParam("brand") String brand,

            @Parameter(description = "Prénom du propriétaire du portfolio", example = "Meryem")
            @RequestParam("firstName") String firstName,

            @Parameter(description = "Nom du propriétaire du portfolio", example = "Ouyouss")
            @RequestParam("lastName") String lastName,

            @Parameter(description = "Pays du propriétaire du portfolio", example = "Maroc")
            @RequestParam("country") String country,

            @Parameter(description = "Domaine du propriétaire du portfolio", example = "Développement web")
            @RequestParam("domain") String domain,

            @Parameter(description = "Email du propriétaire du portfolio", example = "ouyss.meryem@gmail.com")
            @RequestParam("email") String email,

            @Parameter(description = "Numéro de téléphone du propriétaire du portfolio", example = "0651061415")
            @RequestParam("telephone") String telephone,

            @Parameter(description = "Lien Facebook du propriétaire du portfolio", example = "https://facebook.com/")
            @RequestParam("facebookLien") String facebookLien,

            @Parameter(description = "Lien Twitter du propriétaire du portfolio", example = "https://twitter.com/")
            @RequestParam("twiterLien") String twiterLien,

            @Parameter(description = "Lien LinkedIn du propriétaire du portfolio", example = "https://linkedin.com/")
            @RequestParam("linkdnLien") String linkdnLien,

            @Parameter(description = "Lien Instagram du propriétaire du portfolio", example = "https://instagram.com/")
            @RequestParam("instagramLien") String instagramLien,

            @Parameter(description = "Description globale du portfolio", example = "Portfolio professionnel de Ouyouss Meryem")
            @RequestParam("descriptionGlobal") String descriptionGlobal,

            @Parameter(description = "Couleur de fond du portfolio", example = "#FFFFFF")
            @RequestParam("backgroundColor") String backgroundColor,

            @Parameter(description = "Couleur du texte du portfolio", example = "#000000")
            @RequestParam("textColor") String textColor,

            @Parameter(description = "Couleur de décoration du portfolio", example = "#FF5733")
            @RequestParam("decorationColor") String decorationColor,

            @Parameter(description = "Photo sans arrière-plan du propriétaire du portfolio")
            @RequestPart("photoWithoutBackground") MultipartFile photoWithoutBackground,

            @Parameter(description = "Photo du propriétaire du portfolio")
            @RequestPart("photo") MultipartFile photo,

            @Parameter(description = "CV PDF du propriétaire du portfolio")
            @RequestPart("cvPdf") MultipartFile cvPdf,

            @Parameter(description = "Requête JSON pour créer le portfolio", schema = @Schema(type = "string", format = "json"))
            @RequestParam("portfolioRequest") String portfolioRequestJson,

            @Parameter(description = "Images de travail du portfolio")
            @RequestPart("workImages") List<MultipartFile> workImages) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        PortfolioRequest portfolioRequest = objectMapper.readValue(portfolioRequestJson, PortfolioRequest.class);


        Portfolio portfolio =portfolioService.createPortfolio(keycloakUserId,brand, firstName, lastName, country, domain,email
                ,telephone,facebookLien,twiterLien,linkdnLien,instagramLien, descriptionGlobal,
                portfolioRequest.getSkills(), portfolioRequest.getExperiences(),
                portfolioRequest.getEducations(),portfolioRequest.getServices(),
                portfolioRequest.getWorks(),backgroundColor, textColor, decorationColor,workImages);



        String htmlContent = portfolioService.loadFileContent("static/index.html")
                .replace("#{firstName}", firstName)
                .replace("#{lastName}", lastName)
                .replace("#{brand}", brand)
                .replace("#{descriptionGlobal}", descriptionGlobal)
                .replace("#{country}", country)
                .replace("#{domain}", domain)
                .replace("#{email}", email)
                .replace("#{telephone}", telephone)
                .replace("#{facebookLien}", facebookLien)
                .replace("#{twiterLien}", twiterLien)
                .replace("#{linkdnLien}", linkdnLien)
                .replace("#{instagramLien}", instagramLien);


        StringBuilder skillsHtml = new StringBuilder();
        for (SkillDTO skill : portfolioRequest.getSkills()) {
            skillsHtml.append("<li><span>").append(skill.getTitle()).append("</span><br>").append(skill.getDescription()).append("</li>");
        }

        StringBuilder experiencesHtml = new StringBuilder();
        for (ExperienceDTO experience : portfolioRequest.getExperiences()) {
            experiencesHtml.append("<li><span>").append(experience.getDateDebut()).append(" - ")
                    .append(experience.getDateFin()).append("</span><br>").append(experience.getDescription()).append("</li>");
        }

        StringBuilder educationsHtml = new StringBuilder();
        for (EducationDTO education : portfolioRequest.getEducations()) {
            educationsHtml.append("<li><span>").append(education.getEducationYear()).append("</span><br>")
                    .append(education.getDescription()).append("</li>");
        }

        StringBuilder servicesHtml=new StringBuilder();
        for(ServiceDTO serviceDTO:portfolioRequest.getServices()){

            servicesHtml.append("<div><i class='fa-solid fa-code'></i><h2>").append(serviceDTO.getTitle()) .append("</h2><br>")
                    .append("<p>").append(serviceDTO.getDescription()).append("</p></div>");
        }

        StringBuilder worksHtml = new StringBuilder();
        int imageIndex = 0;
        for (WorkDTO workDTO : portfolioRequest.getWorks()) {
            worksHtml.append("<div class=\"work\">")
                    .append("<img src=\"workImage").append(imageIndex).append(".jpg\" >")
                    .append("<div class=\"layer\">")
                    .append("<h3>").append(workDTO.getTitle()).append("</h3>")
                    .append("<p>").append(workDTO.getDescription()).append("</p>")
                    .append("<a href=\"").append(workDTO.getLink()).append("\"><i class='fa-solid fa-up-right-from-square'></i></a>")
                    .append("</div></div>");

            imageIndex++;
        }

        htmlContent = htmlContent
                .replace("#{skillsList}", skillsHtml.toString())
                .replace("#{experiencesList}", experiencesHtml.toString())
                .replace("#{educationsList}", educationsHtml.toString())
                .replace("#{servicesList}", servicesHtml.toString())
                .replace("#{worksList}", worksHtml.toString());

        String cssContent =portfolioService.loadFileContent("static/style.css")
                .replace("#{backgroundColor}", backgroundColor)
                .replace("#{textColor}", textColor)
                .replace("#{decorationColor}", decorationColor)
                .replace("#{secondDecorationColor}",portfolioService.getDarkenedColor(decorationColor));

        String jsContent = portfolioService.loadFileContent("static/script.js");


        File tempDir = Files.createTempDirectory("portfolio").toFile();
        File htmlFile = new File(tempDir, "index.html");
        File cssFile = new File(tempDir, "style.css");
        File jsFile = new File(tempDir, "script.js");
        File photoWithoutBackgroundFile = new File(tempDir, "photoWithoutBackground.png");
        File photoFile = new File(tempDir, "photo.jpg");
        File cvPdfFile = new File(tempDir, "cvPdf.pdf");


        FileUtils.writeStringToFile(htmlFile, htmlContent, "UTF-8");
        FileUtils.writeStringToFile(cssFile, cssContent, "UTF-8");
        FileUtils.writeStringToFile(jsFile, jsContent, "UTF-8");
        FileCopyUtils.copy(photoWithoutBackground.getBytes(), photoWithoutBackgroundFile);
        FileCopyUtils.copy(photo.getBytes(), photoFile);
        FileCopyUtils.copy(cvPdf.getBytes(), cvPdfFile);

        imageIndex = 0;
        for (MultipartFile workImage : workImages) {
            File workImageFile = new File(tempDir, "workImage" + imageIndex + ".jpg");
            FileCopyUtils.copy(workImage.getBytes(), workImageFile);
            imageIndex++;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOut = new ZipOutputStream(byteArrayOutputStream)) {
            portfolioService.addToZipFile(htmlFile, "index.html", zipOut);
            portfolioService.addToZipFile(cssFile, "style.css", zipOut);
            portfolioService.addToZipFile(jsFile, "script.js", zipOut);
            portfolioService.addToZipFile(photoWithoutBackgroundFile, "photoWithoutBackground.png", zipOut);
            portfolioService.addToZipFile(photoFile, "photo.jpg", zipOut);
            portfolioService.addToZipFile(cvPdfFile, "cvPdf.pdf", zipOut);

            imageIndex = 0;
            for (MultipartFile workImage : workImages) {
                portfolioService.addToZipFile(new File(tempDir, "workImage" + imageIndex + ".jpg"), "workImage" + imageIndex + ".jpg", zipOut);
                imageIndex++;
            }
            zipOut.closeEntry();
        }

        byte[] zipBytes = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=portfolio.zip");

        return new ResponseEntity<>(zipBytes, headers, HttpStatus.OK);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}