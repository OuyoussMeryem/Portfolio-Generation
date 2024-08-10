import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PortfolioFormComponent} from "./components/portfolio-form/portfolio-form.component";
import {HomeComponent} from "./components/home/home.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {AuthGuard} from "./guards/auth.guard";
import {DetailsPortfolioComponent} from "./components/details-portfolio/details-portfolio.component";
import {PortfoliosComponent} from "./components/portfolios/portfolios.component";

const routes: Routes = [
  {path:"portfolioForm",component:PortfolioFormComponent,canActivate:[AuthGuard],data:{roles:['USER']}},
  {path:"",component:HomeComponent},
  {path:"profile",component:ProfileComponent,canActivate:[AuthGuard],data:{roles:['USER']}},
  {path:"detailsPortfolio/:portfolioId",component:DetailsPortfolioComponent,data:{roles:['USER']}},
  {path:"portfolios",component:PortfoliosComponent,canActivate:[AuthGuard],data:{roles:['USER']}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
