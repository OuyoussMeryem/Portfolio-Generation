import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PortfolioFormComponent} from "./portfolio-form/portfolio-form.component";
import {PortfolioTestComponent} from "./portfolio-test/portfolio-test.component";
import {HomeComponent} from "./home/home.component";
import {AboutComponent} from "./about/about.component";

const routes: Routes = [
  {path:"portfolioForm",component:PortfolioFormComponent},
  {path:"portfoliotest",component:PortfolioTestComponent},
  {path:"homePage",component:HomeComponent},
  {path:"about",component:AboutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
