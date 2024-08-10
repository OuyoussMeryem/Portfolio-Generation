package ma.ensa.portfoliobackendapp.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
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

    @PostMapping(value = "/portfolio", consumes = "multipart/form-data")
    public ResponseEntity<byte[]> generatePortfolioSeparer(
            @RequestParam("keycloakUserId") String keycloakUserId,
            @RequestParam("brand") String brand,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("country") String country,
            @RequestParam("domain") String domain,
            @RequestParam("email") String email,
            @RequestParam("telephone") String telephone,
            @RequestParam("facebookLien") String facebookLien,
            @RequestParam("twiterLien") String twiterLien,
            @RequestParam("linkdnLien") String linkdnLien,
            @RequestParam("instagramLien") String instagramLien,
            @RequestParam("descriptionGlobal") String descriptionGlobal,
            @RequestParam("backgroundColor") String backgroundColor,
            @RequestParam("textColor") String textColor,
            @RequestParam("decorationColor") String decorationColor,
            @RequestPart("photoWithoutBackground") MultipartFile photoWithoutBackground,
            @RequestPart("photo") MultipartFile photo,
            @RequestPart("cvPdf") MultipartFile cvPdf,
            @RequestParam("portfolioRequest") String portfolioRequestJson,
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