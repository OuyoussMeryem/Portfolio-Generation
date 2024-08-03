import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PortfolioFormComponent} from "./portfolio-form/portfolio-form.component";

const routes: Routes = [
  {path:"portfolioForm",component:PortfolioFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
