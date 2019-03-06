import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthorizationService } from '../../services/authorization.service';
import { Router } from '@angular/router';
import { AuthorizationDetails } from '../../models/authorization-details';





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
      console.log("err");
    }); 
  }

  

}
