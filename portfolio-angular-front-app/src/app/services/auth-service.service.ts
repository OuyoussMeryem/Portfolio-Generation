import { Injectable } from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  public profile?:KeycloakProfile;
  constructor(private keycloakService:KeycloakService ) {
  }

  getUserId(): string | undefined | null {
    if (this.keycloakService.isLoggedIn()) {
      const userProfile = this.keycloakService.getKeycloakInstance().profile;
      return userProfile ? userProfile.id : null;
    }
    return null;
  }

  async getProfile(): Promise<KeycloakProfile | undefined> {
    if (await this.keycloakService.isLoggedIn()) {
      this.profile = await this.keycloakService.loadUserProfile();
      return this.profile;
    }
    return undefined;
  }

}
