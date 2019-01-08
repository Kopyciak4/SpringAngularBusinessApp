import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user'
import { Observable } from 'rxjs';
import { Account } from '../models/account';


@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  constructor( private httpClient:HttpClient ) {}
  

  login(user: User){
      return this.httpClient.get('http://localhost:8080/account/login', {headers: new  HttpHeaders({ 
        "Authorization": "Basic " + btoa( user.login+ ':' + user.password)
      }),
       observe: 'response'
    });
  }

  logout(){
    return this.httpClient.post('http://localhost:8080/account/logout', {})
    
  }

  getAccounts() :Observable<Account[]> {
    return this.httpClient.get<Account[]>("http://localhost:8080/accounts"); 
  }
  
  registerAccount(account: Account) {
    return this.httpClient.post("http://localhost:8080/account/register", 
    account
    );
  }

  getAccount(login: string) :Observable<Account>{
    return this.httpClient.get<Account>('http://localhost:8080/accounts/' + login); 
  }


} 
