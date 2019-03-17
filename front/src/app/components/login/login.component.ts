import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthorizationService } from '../../services/authorization.service';
import { Router } from '@angular/router';
import { AuthorizationDetails } from '../../models/authorization-details';
import { ToastrService } from 'ngx-toastr';




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: User = {
    login: '',
    password: '', 
    userID: 0,
    tasks: []
  }  

  constructor(
    private auth: AuthorizationService,
    private router:Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    
  }

  login() {
    this.auth.login(this.user).subscribe((response: any)=> {
      const loginDetails: AuthorizationDetails = {
        token: response.headers.get("Authorization"),
        role: response.body.role,
        login: this.user.login
      };
      localStorage.setItem("authorizationDetails", JSON.stringify(loginDetails));
      this.router.navigate(['welcome']);
    },
    err => {
      if (err.status === 401) {
        this.toastr.error("Bad login or password", "Authorization failed");
      } else {
        this.toastr.error("Some problems on serverside", "Request failed");
      }
    });
  }
}
