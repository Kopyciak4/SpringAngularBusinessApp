import {Injectable} from '@angular/core';
import {Router, CanActivate} from '@angular/router';


@Injectable()
export class RouteGuard implements CanActivate{
    constructor(
        private router:Router
    ){}

    canActivate(){
        if(!localStorage.getItem("authorizationDetails")){
           return true; 
        }else { 
            this.router.navigate(['/welcome']);
            return false;
        }
    }
} 

