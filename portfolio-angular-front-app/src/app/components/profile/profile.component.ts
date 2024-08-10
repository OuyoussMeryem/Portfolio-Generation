import {Component, OnInit} from '@angular/core';
import {KeycloakProfile} from "keycloak-js";
import {AuthServiceService} from "../../services/auth-service.service";
import {KeycloakService} from "keycloak-angular";
import {PortfolioService} from "../../services/portfolio.service";
import {PortfolioModel} from "../../models/portfolio.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  public profile?:KeycloakProfile;
  public portfolios: PortfolioModel[] = [];

  constructor(private authService:AuthServiceService,
              private keycloakService:KeycloakService,
              private portfolioService:PortfolioService,
              private router:Router) {
  }

  ngOnInit(): void {
    this.loadUserProfile();
  }

  async loadUserProfile() {
    this.profile = await this.authService.getProfile();
    if(this.profile?.id){
      this.portfolioService.getPortfoliosByUserId(this.profile.id).subscribe({
        next:(data)=>{
          this.portfolios=data;
          console.log(this.portfolios);
        },
        error:(err)=>{
          console.error('Erreur lors de la récupération des portfolios:', err);
        }
      });
    }

  }

  portfolioDetails(portfolio:PortfolioModel) {
  this.router.navigateByUrl("detailsPortfolio/"+portfolio.id);
  }
}
