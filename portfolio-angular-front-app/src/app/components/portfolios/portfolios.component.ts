import {Component, OnInit} from '@angular/core';
import {PortfolioService} from "../../services/portfolio.service";

@Component({
  selector: 'app-portfolios',
  templateUrl: './portfolios.component.html',
  styleUrl: './portfolios.component.css'
})
export class PortfoliosComponent implements  OnInit{

  public portfoliosByUser: any[] = [];
  public totalPortfolios: number = 0;

  constructor(private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    this.portfolioService.getPortfoliosSummary().subscribe(data => {
      this.portfoliosByUser = data.portfoliosByUser;
      this.totalPortfolios = data.totalPortfolios;
    });
  }

}
