import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { User } from '../models/user'
import { Observable } from 'rxjs';
import { Account } from '../models/account';
import { AuthorizationDetails } from "../models/authorization-details";

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

  updateAccount(account: Account) {
    return this.httpClient.put("http://localhost:8080/accounts/update", account);
  }

  deleteAccount(accountID: number) {
    let params = new HttpParams().set('accountID', accountID.toString())

    return this.httpClient.delete("http://localhost:8080/accounts", {params:params}); 
  }

  isAdmin(): boolean {

    let authDetails = localStorage.getItem("authorizationDetails");
    let userRole;
    if(authDetails){
      userRole =  (<AuthorizationDetails>JSON.parse(authDetails)).role;
    }
    return userRole === "ROLE_ADMIN";
  }

  getLogin(): string {
    let authDetails = localStorage.getItem("authorizationDetails");
    let login;
    if(authDetails){
      login =  (<AuthorizationDetails>JSON.parse(authDetails)).login;
    }
    return login; 
    
  }

}
 

 
