import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PortfolioService} from "../../services/portfolio.service";
import {PortfolioModel} from "../../models/portfolio.model";

@Component({
  selector: 'app-details-portfolio',
  templateUrl: './details-portfolio.component.html',
  styleUrl: './details-portfolio.component.css'
})
export class DetailsPortfolioComponent implements OnInit{
  portfolioId!:number;
  portfolio!:PortfolioModel;

  constructor(private activatedRoute:ActivatedRoute,private portfolioService:PortfolioService) {
    this.portfolioId=this.activatedRoute.snapshot.params["portfolioId"];
    console.log(this.portfolioId);
  }

  ngOnInit(): void {
    this.portfolioService.getPortfolioBuId(this.portfolioId).subscribe({
      next:(data)=>{
        this.portfolio=data;
        console.log(this.portfolio);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

}
