import { Injectable, IterableDiffers } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user'


@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  constructor( private httpClient:HttpClient ) {}
  

  login(user: User){
      return this.httpClient.post('http://localhost:8080/account/login', {}, {headers: new  HttpHeaders({ 
        "Authorization": "Basic " + btoa( user.login+ ':' + user.password)
      }),
       observe: 'response'
    } );
  
  }
} 
