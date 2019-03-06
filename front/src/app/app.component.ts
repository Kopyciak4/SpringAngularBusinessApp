import { Component } from '@angular/core';
import { AuthorizationService } from './services/authorization.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(
    private auth: AuthorizationService,
    private router: Router,
  ){}

  title = 'app';
  state = false;



  changeSlidebarState(){
    this.state = !this.state
  }

  logout(){
    this.auth.logout().subscribe((response: any)=> {
      localStorage.removeItem("authorizationDetails");
      this.router.navigate(['login']);
    },
    err => {
      console.log("nieok")
    })
  }


  

}
