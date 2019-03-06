import { Component, OnInit, Input } from '@angular/core';
import { Task } from 'src/app/models/task';

@Component({
  selector: 'app-user-tasks-list',
  templateUrl: './user-tasks-list.component.html',
  styleUrls: ['./user-tasks-list.component.scss']
})
export class UserTasksListComponent implements OnInit {

  @Input() 
  tasks: Task[];
  

  constructor() { 
    
  }

  ngOnInit() {
    console.log(1234);
    console.log(this.tasks);
  }

}
