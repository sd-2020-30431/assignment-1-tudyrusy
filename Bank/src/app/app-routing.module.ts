import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {RegisterComponent} from './register/register.component';
import {WelcomeComponent} from './welcome/welcome.component';

const routes: Routes = [
  {path: '', component: LoginComponent}, {path: 'register', component: RegisterComponent},
  {path: 'welcome', component: WelcomeComponent}, {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes), HttpClientModule, BrowserModule, FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
