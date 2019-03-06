import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthorizationDetails } from "../models/authorization-details";


@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler):Observable<HttpEvent<any>> {
    let authorizationDetails = localStorage.getItem("authorizationDetails");
    if(authorizationDetails) {
      let authToken = (<AuthorizationDetails>JSON.parse(authorizationDetails)).token;
      req = req.clone({
        headers: req.headers.set('Authorization', authToken)
      })
      
    }
    return next.handle(req);
  }
}
