import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import {Observable} from "rxjs";
import {PortfolioModel} from "../models/portfolio.model";

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {

  private apiUrl = 'http://localhost:8088/api/generate';

  constructor(private http: HttpClient) { }

  generatePortfolio(formData: FormData): Observable<Blob> {
    const headers = new HttpHeaders({
      'Accept': 'application/zip',
    });

    return this.http.post(`${this.apiUrl}/portfolio`, formData, { headers: headers, responseType: 'blob' });
  }


  getPortfoliosByUserId(userId: string): Observable<PortfolioModel[]> {
    return this.http.get<PortfolioModel[]>(`${this.apiUrl}/user/${userId}/portfolios`);
  }


  getPortfolioBuId(portfolioId:number): Observable<PortfolioModel> {
    return this.http.get<PortfolioModel>(`${this.apiUrl}/portfolio/${portfolioId}`);
  }

  getPortfoliosSummary(): Observable<any> {
    return this.http.get('http://localhost:8088/admin/portfolios-summary');
  }

}
