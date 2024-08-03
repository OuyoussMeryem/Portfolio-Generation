import {Component, OnInit} from '@angular/core';
import {PortfolioService} from "../services/portfolio.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-portfolio-form',
  templateUrl: './portfolio-form.component.html',
  styleUrl: './portfolio-form.component.css'
})
export class PortfolioFormComponent implements OnInit{

  public portfolioForm!: FormGroup;
  photoWithoutBackground!: File;
  photo!: File;

  constructor(private portfolioService:PortfolioService,private fb:FormBuilder) {
  }

  ngOnInit(): void {
    this.portfolioForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      photoWithoutBackground: [null, Validators.required],
      photo: [null, Validators.required]
    });
  }
  onFileChange(event: any, fileType: string) {
    const file = event.target.files[0];
    if (fileType === 'photoWithoutBackground') {
      this.photoWithoutBackground = file;
      this.portfolioForm.patchValue({ photoWithoutBackground: file });
    } else if (fileType === 'photo') {
      this.photo = file;
      this.portfolioForm.patchValue({ photo: file });
    }
  }

  onSubmit() {
    if (this.portfolioForm.invalid) {
      return;
    }

    const formData = new FormData();
    formData.append('firstName', this.portfolioForm.get('firstName')?.value);
    formData.append('lastName', this.portfolioForm.get('lastName')?.value);
    formData.append('photoWithoutBackground', this.photoWithoutBackground);
    formData.append('photo', this.photo);

    this.portfolioService.generatePortfolio(formData).subscribe(response => {
      const url = window.URL.createObjectURL(response);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'portfolio.zip';
      a.click();
      window.URL.revokeObjectURL(url);
    }, error => {
      console.error('Error:', error);
    });
  }

}
