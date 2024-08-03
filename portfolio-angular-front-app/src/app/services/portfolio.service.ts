import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {

  private apiUrl='http://localhost:8088/api/generate/htmlCssJsFilesSeparer';

  constructor(private http:HttpClient) { }

  generatePortfolio(formData:FormData):Observable<Blob>{
    const headers=new HttpHeaders({
      'enctype':'multipart/form-data'
    });
    return this.http.post(this.apiUrl,formData,{headers:headers,responseType: 'blob'})
  }
}
