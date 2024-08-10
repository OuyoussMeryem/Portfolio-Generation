import {Component, OnInit} from '@angular/core';
import {KeycloakProfile} from "keycloak-js";
import {KeycloakService} from "keycloak-angular";
import {AuthServiceService} from "./services/auth-service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'portfolio-angular-front-app';
  public profile?:KeycloakProfile;

  constructor(public keycloakService:KeycloakService,public authService:AuthServiceService) {
  }



  ngOnInit(): void {
    this.loadUserProfile();
  }

  async loadUserProfile() {
    this.profile = await this.authService.getProfile();
    console.log(this.profile);
  }

  async login() {
    await this.keycloakService.login({
      redirectUri:window.location.origin
    })
  }


  logout() {
    this.keycloakService.logout(window.location.origin);
  }

}
