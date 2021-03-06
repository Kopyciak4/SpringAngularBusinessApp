import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';
import { AuthGuard } from './guard/auth.guard';
import { RouteGuard } from './guard/route.guard';
import { RoleGuard } from './guard/role.guard';

import { AuthorizationService } from './services/authorization.service';
import { InterceptorService } from './services/interceptor.service';

import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { AgGridModule } from 'ag-grid-angular';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';


import { AppComponent } from './app.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { LoginComponent } from './components/login/login.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { EmployeesPageComponent } from './components/employees-page/employees-page.component';
import { ProfileComponent } from './components/profile/profile.component';
import { TasksPageComponent } from './components/tasks-page/tasks-page.component';
import { TaskManagementComponent } from './components/task-management/task-management.component';
import { UserTasksListComponent } from './components/user-tasks-list/user-tasks-list.component';






const appRoutes: Routes = [
  {path:'', redirectTo: '/login', pathMatch: 'full' },
  {path:'login', component: LoginComponent, canLoad:[RouteGuard]},
  {path:'welcome', component: WelcomeComponent,  canActivate:[AuthGuard]},
  {path:'employees', component: EmployeesPageComponent,  canActivate:[AuthGuard,RoleGuard]},
  {path:'profile/:login', component: ProfileComponent, canActivate:[AuthGuard, RoleGuard]},
  {path:'tasks', component: TasksPageComponent,  canActivate:[AuthGuard]},
  {path:'task/:taskId', component: TaskManagementComponent,  canActivate:[AuthGuard]},
  
]



@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    LoginComponent,
    WelcomeComponent,
    EmployeesPageComponent,
    ProfileComponent,
    TasksPageComponent,
    TaskManagementComponent,
    UserTasksListComponent,
  ],
  imports: [
    NgbModalModule,
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    AngularFontAwesomeModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      preventDuplicates: true
    }),
    AgGridModule.withComponents([]),
  ],
  providers: [
    AuthorizationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    },
    AuthGuard,
    RouteGuard,
    RoleGuard

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
