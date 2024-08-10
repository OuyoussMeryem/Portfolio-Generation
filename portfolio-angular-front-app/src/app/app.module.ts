import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { PortfolioFormComponent } from './components/portfolio-form/portfolio-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import { DetailsPortfolioComponent } from './components/details-portfolio/details-portfolio.component';
import { PortfoliosComponent } from './components/portfolios/portfolios.component';

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'generate-portfolio-realm',
        clientId: 'angular-portfolio-client'
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}
@NgModule({
  declarations: [
    AppComponent,
    PortfolioFormComponent,
    HomeComponent,
    ProfileComponent,
    DetailsPortfolioComponent,
    PortfoliosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    KeycloakAngularModule
  ],
  providers: [
    provideAnimationsAsync(),
    {provide : APP_INITIALIZER, deps : [KeycloakService],useFactory : initializeKeycloak, multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
