package ma.ensa.portfoliobackendapp.controllers;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@RestController
@RequestMapping("/api/generate")
@CrossOrigin("*")
public class GeneratePortfolioController {

    @Autowired
    private ResourceLoader resourceLoader;
    @PostMapping("/htmlCssJsFilesSeparer")
    public ResponseEntity<byte[]> generatePortfolioSeparer(@RequestParam("firstName") String firstName,
                                                           @RequestParam("lastName") String lastName,
                                                           @RequestParam("photoWithoutBackground") MultipartFile photoWithoutBackground,
                                                           @RequestParam("photo") MultipartFile photo) throws IOException {

        // Charger le contenu des fichiers HTML, CSS et JS depuis les ressources statiques
        String htmlContent = loadFileContent("static/index.html")
                .replace("#{firstName}", firstName)
                .replace("#{lastName}", lastName);

        String cssContent = loadFileContent("static/style.css");
        String jsContent = loadFileContent("static/script.js");

        // Création d'un répertoire temporaire pour les fichiers
        File tempDir = Files.createTempDirectory("portfolio").toFile();
        File htmlFile = new File(tempDir, "index.html");
        File cssFile = new File(tempDir, "style.css");
        File jsFile = new File(tempDir, "script.js");
        File photoWithoutBackgroundFile = new File(tempDir, "photoWithoutBackground.png");
        File photoFile = new File(tempDir, "photo.jpg");

        // Écriture des fichiers
        FileUtils.writeStringToFile(htmlFile, htmlContent, "UTF-8");
        FileUtils.writeStringToFile(cssFile, cssContent, "UTF-8");
        FileUtils.writeStringToFile(jsFile, jsContent, "UTF-8");
        FileCopyUtils.copy(photoWithoutBackground.getBytes(), photoWithoutBackgroundFile);
        FileCopyUtils.copy(photo.getBytes(), photoFile);

        // Utilisation de ByteArrayOutputStream pour gérer les flux en mémoire
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOut = new ZipOutputStream(byteArrayOutputStream)) {
            addToZipFile(htmlFile, "index.html", zipOut);
            addToZipFile(cssFile, "style.css", zipOut);
            addToZipFile(jsFile, "script.js", zipOut);
            addToZipFile(photoWithoutBackgroundFile, "photoWithoutBackground.png", zipOut);
            addToZipFile(photoFile, "photo.jpg", zipOut);
            zipOut.closeEntry();
        }

        // Conversion du ByteArrayOutputStream en tableau de bytes
        byte[] zipBytes = byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=portfolio.zip");

        return new ResponseEntity<>(zipBytes, headers, HttpStatus.OK);
    }

    // Méthode pour charger le contenu d'un fichier en tant que chaîne de caractères
    private String loadFileContent(String filePath) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + filePath);
        InputStream inputStream = resource.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        return new String(bytes);
    }

    // Méthode pour ajouter un fichier au fichier ZIP
    private void addToZipFile(File file, String fileName, ZipOutputStream zipOut) throws IOException {
        zipOut.putNextEntry(new ZipEntry(fileName));
        byte[] bytes = Files.readAllBytes(file.toPath());
        zipOut.write(bytes, 0, bytes.length);
        zipOut.closeEntry();
    }
}

