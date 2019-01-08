import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { AuthGuard } from './guard/auth.guard';
import { RouteGuard } from './guard/route.guard';

import { AuthorizationService } from './services/authorization.service';
import { InterceptorService } from './services/interceptor.service';

import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { AgGridModule } from 'ag-grid-angular';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';


import { AppComponent } from './app.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { EmployeesPageComponent } from './components/employees-page/employees-page.component';
import { ProfileComponent } from './components/profile/profile.component';





const appRoutes: Routes = [
  {path:'', redirectTo: '/login', pathMatch: 'full' },
  {path:'login', component: LoginComponent, canActivate:[RouteGuard]},
  {path:'dashboard', component: DashboardComponent, canActivate:[AuthGuard]},
  {path:'welcome', component: WelcomeComponent,  canActivate:[AuthGuard]},
  {path:'employees', component: EmployeesPageComponent,  canActivate:[AuthGuard]},
  {path:'profile/:login', component: ProfileComponent, canActivate:[AuthGuard]}
  
]



@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    LoginComponent,
    DashboardComponent,
    WelcomeComponent,
    EmployeesPageComponent,
    ProfileComponent,
  ],
  imports: [
    NgbModalModule,
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    AngularFontAwesomeModule,
    HttpClientModule,
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
    RouteGuard

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
