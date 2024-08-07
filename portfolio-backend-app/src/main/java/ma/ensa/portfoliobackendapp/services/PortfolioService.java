package ma.ensa.portfoliobackendapp.services;

import ma.ensa.portfoliobackendapp.dtos.*;
import ma.ensa.portfoliobackendapp.entities.*;
import ma.ensa.portfoliobackendapp.mappers.*;
import ma.ensa.portfoliobackendapp.repositories.PortfolioRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class PortfolioService {


    private ResourceLoader resourceLoader;
    private PortfolioRepository portfolioRepository;
    private UserAppService userAppService;
    private ExperienceService experienceService;
    private EducationService educationService;
    private ServiceService serviceService;
    private WorkService workService;
    private SkillService skillService;
    private ExperienceDTOMapper experienceDTOMapper;
    private EducationDTOMapper educationDTOMapper;
    private ServiceDTOMapper serviceDTOMapper;
    private WorkDTOMapper workDTOMapper;
    private SkillDTOMapper skillDTOMapper;


    public PortfolioService(ResourceLoader resourceLoader,
                            PortfolioRepository portfolioRepository,
                            UserAppService userAppService,
                            ExperienceService experienceService,
                            EducationService educationService,
                            ServiceService serviceService,
                            WorkService workService,
                            SkillService skillService,
                            ExperienceDTOMapper experienceDTOMapper,
                            EducationDTOMapper educationDTOMapper,
                            ServiceDTOMapper serviceDTOMapper,
                            WorkDTOMapper workDTOMapper,
                            SkillDTOMapper skillDTOMapper) {
        this.resourceLoader = resourceLoader;
        this.portfolioRepository = portfolioRepository;
        this.userAppService = userAppService;
        this.experienceService = experienceService;
        this.educationService = educationService;
        this.serviceService = serviceService;
        this.workService = workService;
        this.skillService = skillService;
        this.experienceDTOMapper = experienceDTOMapper;
        this.educationDTOMapper = educationDTOMapper;
        this.serviceDTOMapper = serviceDTOMapper;
        this.workDTOMapper = workDTOMapper;
        this.skillDTOMapper = skillDTOMapper;
    }


    public Portfolio createPortfolio(String brand, String firstName, String lastName, String country, String domain,String email,
                                     String telephone,String facebookLien,
                                     String twiterLien,String linkdnLien,String instagramLien, String descriptionGlobal,
                                     List<SkillDTO> skills, List<ExperienceDTO> experiences, List<EducationDTO> educations,
                                     List<ServiceDTO> services, List<WorkDTO> works, String backgroundColor, String textColor, String decorationColor, List<MultipartFile> workImages) throws IOException {
        Portfolio portfolio = new Portfolio();
        portfolio.setCreatedAt(new Date());
        portfolio.setBrand(brand);
        portfolio.setBackgroundColor(backgroundColor);
        portfolio.setTextColor(textColor);
        portfolio.setDecorationColor(decorationColor);
        portfolio.setDescriptionGlobal(descriptionGlobal);

        UserApp user = new UserApp();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCountry(country);
        user.setDomain(domain);
        user.setEmail(email);
        user.setTelephone(telephone);
        user.setFacebookLink(facebookLien);
        user.setTwitterLink(twiterLien);
        user.setLinkedinLink(linkdnLien);
        user.setInstagramLink(instagramLien);
        portfolio.setUser(user);
        userAppService.save(user);


        List<Skill> skillEntities = skillDTOMapper.dtoToEntity(skills);
        skillService.saveAll(skillEntities);
        portfolio.setSkillList(skillEntities);


        List<Experience> experienceEntities = experienceDTOMapper.dtoToEntity(experiences);
        experienceService.saveAll(experienceEntities);
        portfolio.setExperienceList(experienceEntities);


        List<Education> educationEntities = educationDTOMapper.dtoToEntity(educations);
        educationService.saveAll(educationEntities);
        portfolio.setEducationList(educationEntities);


        List<ma.ensa.portfoliobackendapp.entities.Service> serviceEntities = serviceDTOMapper.dtoToEntity(services);
        serviceService.saveAll(serviceEntities);
        portfolio.setServiceList(serviceEntities);


        List<Work> workEntities = new ArrayList<>();
        for (int i = 0; i < works.size(); i++) {
            WorkDTO workDTO = works.get(i);
            MultipartFile workImage = workImages.get(i);
            Work work = workDTOMapper.dtoToEntity(workDTO);
            work.setImage(workImage.getBytes());
            workService.save(work);
            workEntities.add(work);
        }
        portfolio.setWorkList(workEntities);
        portfolioRepository.save(portfolio);

        return portfolio;
    }

    public String loadFileContent(String path) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + path);
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] fileBytes = FileCopyUtils.copyToByteArray(inputStream);
            return new String(fileBytes);
        }
    }

    public void addToZipFile(File file, String fileName, ZipOutputStream zipOut) throws IOException {
        zipOut.putNextEntry(new ZipEntry(fileName));
        byte[] bytes = Files.readAllBytes(file.toPath());
        zipOut.write(bytes, 0, bytes.length);
        zipOut.closeEntry();
    }

    public String getDarkenedColor(String hexColor) {

        if (hexColor == null || hexColor.length() != 7 || !hexColor.startsWith("#")) {
            return hexColor;
        }
        int r = Integer.parseInt(hexColor.substring(1, 3), 16);
        int g = Integer.parseInt(hexColor.substring(3, 5), 16);
        int b = Integer.parseInt(hexColor.substring(5, 7), 16);

        r = Math.max(0, (int) (r * 0.67));
        g = Math.max(0, (int) (g * 0.67));
        b = Math.max(0, (int) (b * 0.67));


        return String.format("#%02x%02x%02x", r, g, b);
    }



}
