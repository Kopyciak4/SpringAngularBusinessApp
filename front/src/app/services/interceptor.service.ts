import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { ok } from 'assert';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler):Observable<HttpEvent<any>> {
    let token = localStorage.getItem("Token");
    if(token){
      req = req.clone({
        headers: req.headers.set('Authorization', token)
      })
    }
    return next.handle(req);
  }
}
