import {UserAppModel} from "./user-app.model";
import {SkillModel} from "./skill.model";
import {ExperienceModel} from "./experience.model";
import {EducationModel} from "./education.model";
import {ServiceModel} from "./service.model";
import {WorkModel} from "./work.model";

export interface PortfolioModel {
  id: number;
  createdAt: Date;
  brand: string;
  backgroundColor: string;
  textColor: string;
  decorationColor: string;
  descriptionGlobal: string;
  keycloakUserId: string;


  user: UserAppModel;
  skillList: SkillModel[];
  experienceList: ExperienceModel[];
  educationList: EducationModel[];
  serviceList: ServiceModel[];
  workList: WorkModel[];
}
