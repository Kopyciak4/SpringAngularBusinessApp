import { Component, OnInit } from '@angular/core';
import { SidebarElementList } from 'src/app/models/sidebar-element-list';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  
  sidebarList: SidebarElementList[] = [
    {name: 'Employees', link: '/employees'},
    {name: 'Tasks', link: '/tasks'},
    {name: 'Calendar', link: '/calendar'}
  ];

  constructor() { }

  ngOnInit() {
  }

}
