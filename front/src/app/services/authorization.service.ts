import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user'
import { MinLengthValidator } from '@angular/forms';
import { npost } from 'q';



@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  constructor( private httpClient:HttpClient ) {}
  

  login(user: User){
    let headers = {headers: new HttpHeaders(
      {"Authorization": "Basic " + btoa( user.login+ ':' + user.password)}
      )};
      return this.httpClient.get('http://localhost:8080/account/login', headers);
  }

}

