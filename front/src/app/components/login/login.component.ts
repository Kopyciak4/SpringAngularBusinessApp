import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthorizationService } from '../../services/authorization.service';
import {Router} from '@angular/router';





@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: User = {
    login: '', password: ''
  }

  constructor(
    private auth: AuthorizationService,
    private router:Router,
  ) { }

  ngOnInit() {
  }

  login() {
    this.auth.login(this.user).subscribe(response => {
      console.log(response);
      this.router.navigate(['dashboard']);
    },
    err => {
      console.log("err");
    }
    );
  
  }



}