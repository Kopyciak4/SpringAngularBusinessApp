import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Account } from '../../models/account';
import { AuthorizationService } from '../../services/authorization.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  account: Account = {
    name: '',
    surname: '',
    email: '',
    adress: '',
    accountID: 0,
    user: {
      login: '',
      password: '',
      userID: 0
    }
  }

  constructor(
    private route: ActivatedRoute,
    private auth: AuthorizationService,
    private router: Router,

  
  ) { 
    this.route.params.subscribe(params => {
      this.account.user.login = params.login
      this.auth.getAccount(params.login).subscribe((res:Account) => {
        if(res){
          this.account = res;
        } 
      })
    })
   
  }

  ngOnInit() {
  }

  saveAccount(){
    if (!this.account.accountID) {
      this.auth.registerAccount(this.account).subscribe(res=> {
        console.log(1);
        this.router.navigate(['employees']); 
      });
    }else {
      this.auth.updateAccount(this.account).subscribe(res =>{
        console.log(2);
        this.router.navigate(['employees']); 
      });
    }
    
   
  }


}
