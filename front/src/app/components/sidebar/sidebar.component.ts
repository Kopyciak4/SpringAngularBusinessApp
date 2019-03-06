import { Component, OnInit } from '@angular/core';
import { SidebarElementList } from 'src/app/models/sidebar-element-list';
import { AuthorizationService } from '../../services/authorization.service'


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  
  sidebarList: SidebarElementList[] = [
    {name: 'Employees', link: '/employees', onlyForAdmin: true},
    {name: 'Tasks', link: '/tasks', onlyForAdmin: false},
    {name: 'Profile', link:'/profile/'+ this.auth.getLogin(), onlyForAdmin: false},
  ];

  constructor(
    private auth: AuthorizationService
  ) { 
    this.sidebarList = this.sidebarList.filter((el)=> 
      (el.onlyForAdmin && this.auth.isAdmin()) || !el.onlyForAdmin
    )

  }

  ngOnInit() {
  }


}
