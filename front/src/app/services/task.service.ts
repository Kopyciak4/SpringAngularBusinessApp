import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Task } from '../models/task';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(
    private httpClient: HttpClient
  ) {
    
   }

  getTasks():Observable<Task[]> {
    return this.httpClient.get<Task[]>("http://localhost:8080/tasks"); 
  }

  deleteTask(taskId: number) {
    let params = new HttpParams().set('taskId', taskId.toString())
    return this.httpClient.delete("http://localhost:8080/tasks", {params:params}); 
  } 
  
  getTask(taskId: number) :Observable<Task>{
    return this.httpClient.get<Task>('http://localhost:8080/tasks/' + taskId);
  }

  createTask(task: Task) {
    return this.httpClient.post('http://localhost:8080/tasks', task) 
  }

  updateTask(task: Task) {
    return this.httpClient.put('http://localhost:8080/tasks/update', task)
  }

}
