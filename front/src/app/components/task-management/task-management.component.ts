import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Task } from '../../models/task';
import { ActivatedRoute } from '@angular/router';
import { TaskService } from '../../services/task.service';
import { AuthorizationService } from '../../services/authorization.service'
import { Account } from '../../models/account';
import { User } from '../../models/user';

@Component({
  selector: 'app-task-management',
  templateUrl: './task-management.component.html',
  styleUrls: ['./task-management.component.scss']
})
export class TaskManagementComponent implements OnInit {

  task: any = {};

  accounts: Account[];
  
  editMode = false;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private taskService: TaskService,
    private auth: AuthorizationService,

  ) { 
    //pobieranie wartosci z url
    console.log(1);
    this.route.params.subscribe(params => {
      console.log(2);
      this.task.taskId = params.taskId;
      this.taskService.getTask(params.taskId).subscribe((res:Task) => {
        console.log(res);
        console.log(12345);
        if(res){
          this.editMode = true;
          this.task = res;
        }
      })
    })

    if(auth.isAdmin()) {
      this.auth.getAccounts().subscribe((res: Account[])=> {
        this.accounts = res;
      });
    }

   
  }

  ngOnInit() {
  }

  saveTask(){
    if(this.editMode) {
      this.taskService.updateTask(this.task).subscribe(() => {
        this.router.navigate(['tasks']);
      })
    }else {
      this.taskService.createTask(this.task).subscribe(()=>{
        this.router.navigate(['tasks']);
      })
    }
  }

  compareFn(taskOwner: User , user: User):boolean {
    return taskOwner && user && taskOwner.userID === user.userID;
  }

  returnToTasks(){
    this.router.navigate(['tasks']);
  }

}

