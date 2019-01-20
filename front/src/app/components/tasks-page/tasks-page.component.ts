import { Component, OnInit } from '@angular/core';
import { Task } from '../../models/task';
import { TaskService } from '../../services/task.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tasks-page',
  templateUrl: './tasks-page.component.html',
  styleUrls: ['./tasks-page.component.scss']
})
export class TasksPageComponent implements OnInit {

  private columnDefs = [];
	private rowData = [];
	private rowSelection = "single";
	private gridColumnApi;
	private gridApi;
	private selectedTask; 

  constructor(
		private task: TaskService,
		private router: Router,
  ) {
    

    this.task.getTasks().subscribe((response: Task[])=> {
			let row;
			for (let i = 0; i<response.length; i++){ 
				 	row  = {
						taskName: response[i].taskName,
            taskDescrption: response[i].taskDescription,
            taskId: response[i].taskId
				}  
				this.rowData.push(row);
				
			}
			this.columnDefs = Object.keys(row).map(key => {
				
				return {
					headerName: key.toUpperCase(),
					field: key
				}
			});
			console.log(this.rowData); 
		},
		err => {
			console.log("err"); 
		});

   }

  ngOnInit() {
  }

  onFirstDataRendered(params) {
		params.api.sizeColumnsToFit();
	}

	onGridReady(params) {
		this.gridApi = params.api;
	}

	onSelectionChanged() {
		this.selectedTask = this.gridApi.getSelectedRows()[0];
	}

	deleteTask() {
		this.task.deleteTask(this.selectedTask.taskId).subscribe(res =>{
			this.rowData = this.rowData.filter((row) => row.taskId != this.selectedTask.taskId );
			this.selectedTask = null;
		});
	}

	addTask() {
		let idCounter = 0;
		for(let i = 0; i< this.rowData.length; i++) {	
			idCounter = this.rowData[i].taskId + 1
		}
		console.log(idCounter);
		
		
	
			
			


	}

}
