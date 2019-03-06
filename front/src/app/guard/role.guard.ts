import {Injectable} from '@angular/core';
import {Router, CanActivate, RouterStateSnapshot, Params, ActivatedRouteSnapshot} from '@angular/router';
import { AuthorizationService } from "../services/authorization.service"; 


@Injectable()
export class RoleGuard implements CanActivate{
    constructor(
        private router:Router,
        private auth:AuthorizationService,
        
    ){
        
    }

    canActivate(
        route: ActivatedRouteSnapshot
    ){
        if(this.auth.isAdmin() || route.params['login']== this.auth.getLogin()) {
            return true;
        } else {
            this.router.navigate(['/welcome']);
            return false;
        }
    }


} 