import { Component, OnInit } from '@angular/core';
import { PortfolioService } from '../../services/portfolio.service';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthServiceService} from "../../services/auth-service.service";

@Component({
  selector: 'app-portfolio-form',
  templateUrl: './portfolio-form.component.html',
  styleUrls: ['./portfolio-form.component.css']
})
export class PortfolioFormComponent implements OnInit {

  portfolioForm!: FormGroup;
  keycloakUserId!:string | undefined | null;

  constructor(private fb: FormBuilder, private portfolioService: PortfolioService,private authServiceService:AuthServiceService) {}


  ngOnInit() {
    this.portfolioForm = this.fb.group({
      brand: [''],
      firstName: [''],
      lastName: [''],
      country: [''],
      domain: [''],
      email: [''],
      telephone: [''],
      facebookLien: [''],
      twiterLien: [''],
      linkdnLien: [''],
      instagramLien: [''],
      descriptionGlobal: [''],
      backgroundColor: [''],
      textColor: [''],
      decorationColor: [''],
      photoWithoutBackground: [null],
      photo: [null],
      cvPdf: [null],
      portfolioRequest: this.fb.group({
        skills: this.fb.array([]),
        experiences: this.fb.array([]),
        educations: this.fb.array([]),
        services: this.fb.array([]),
        works: this.fb.array([])
      }),
      workImages: this.fb.array([])
    });
  }

  get skills(): FormArray {
    return this.portfolioForm.get('portfolioRequest.skills') as FormArray;
  }

  get experiences(): FormArray {
    return this.portfolioForm.get('portfolioRequest.experiences') as FormArray;
  }

  get educations(): FormArray {
    return this.portfolioForm.get('portfolioRequest.educations') as FormArray;
  }

  get services(): FormArray {
    return this.portfolioForm.get('portfolioRequest.services') as FormArray;
  }

  get works(): FormArray {
    return this.portfolioForm.get('portfolioRequest.works') as FormArray;
  }



  addSkill() {
    const skills = this.portfolioForm.get('portfolioRequest.skills') as FormArray;
    skills.push(this.fb.group({
      title: [''],
      description: ['']
    }));
  }

  addExperience() {
    const experiences = this.portfolioForm.get('portfolioRequest.experiences') as FormArray;
    experiences.push(this.fb.group({
      dateDebut: [''],
      dateFin: [''],
      description: ['']
    }));
  }

  addEducation() {
    const educations = this.portfolioForm.get('portfolioRequest.educations') as FormArray;
    educations.push(this.fb.group({
      educationYear: [''],
      description: ['']
    }));
  }

  addService() {
    const services = this.portfolioForm.get('portfolioRequest.services') as FormArray;
    services.push(this.fb.group({
      title: [''],
      description: ['']
    }));
  }

  addWork() {
    const works = this.portfolioForm.get('portfolioRequest.works') as FormArray;
    works.push(this.fb.group({
      title: [''],
      description: [''],
      link: [''],
      image: [null]
    }));
  }

  addWorkImage(event: any) {
    const files = event.target.files;
    const workImages = this.portfolioForm.get('workImages') as FormArray;
    for (const file of files) {
      workImages.push(new FormControl(file));
    }
  }

  onFileChange(event: Event, field: string) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.portfolioForm.patchValue({ [field]: file });
    }
  }


  onSubmit() {
    console.log("je suis la ");

    this.keycloakUserId=this.authServiceService.getUserId();
    console.log(this.keycloakUserId);
    const formData = new FormData();
    formData.append('keycloakUserId', this.keycloakUserId || '');
    formData.append('brand', this.portfolioForm.get('brand')?.value);
    formData.append('firstName', this.portfolioForm.get('firstName')?.value);
    formData.append('lastName', this.portfolioForm.get('lastName')?.value);
    formData.append('country', this.portfolioForm.get('country')?.value);
    formData.append('domain', this.portfolioForm.get('domain')?.value);
    formData.append('email', this.portfolioForm.get('email')?.value);
    formData.append('telephone', this.portfolioForm.get('telephone')?.value);
    formData.append('facebookLien', this.portfolioForm.get('facebookLien')?.value);
    formData.append('twiterLien', this.portfolioForm.get('twiterLien')?.value);
    formData.append('linkdnLien', this.portfolioForm.get('linkdnLien')?.value);
    formData.append('instagramLien', this.portfolioForm.get('instagramLien')?.value);
    formData.append('descriptionGlobal', this.portfolioForm.get('descriptionGlobal')?.value);
    formData.append('backgroundColor', this.portfolioForm.get('backgroundColor')?.value);
    formData.append('textColor', this.portfolioForm.get('textColor')?.value);
    formData.append('decorationColor', this.portfolioForm.get('decorationColor')?.value);

    // Append files
    const files = ['photoWithoutBackground', 'photo', 'cvPdf'];
    files.forEach(fileField => {
      const file = this.portfolioForm.get(fileField)?.value;
      if (file) {
        formData.append(fileField, file);
      }
    });

    // Append portfolioRequest as JSON
    formData.append('portfolioRequest', JSON.stringify(this.portfolioForm.get('portfolioRequest')?.value));

    // Append workImages
    const workImages = this.portfolioForm.get('workImages')?.value;
    workImages.forEach((image: File) => {
      formData.append('workImages', image, image.name);
    });

    console.log("data",formData);

    this.portfolioService.generatePortfolio(formData).subscribe(response => {
      const url = window.URL.createObjectURL(response);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'portfolio.zip';
      a.click();
      window.URL.revokeObjectURL(url);

      this.portfolioForm.reset();
    });
  }

  removeSkill(index: number): void {
    const skills = this.skills;
    if (skills.length > 1) {
      skills.removeAt(index);
    }
  }

  removeExperience(index: number) {
    const experiences = this.experiences;
    if (experiences.length > 1) {
      experiences.removeAt(index);
    }
  }

  removeEducations(index: number) {
    const educations = this.educations;
    if (educations.length > 1) {
      educations.removeAt(index);
    }
  }

  removeServices(index: number) {
    const services = this.services;
    if (services.length > 1) {
      services.removeAt(index);
    }
  }

  removeWorks(index: number) {
    const works = this.works;
    if (works.length > 1) {
      works.removeAt(index);
    }
  }
}
