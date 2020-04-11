import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {RegisterRequestModel} from './register.request.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerRequest: RegisterRequestModel = new RegisterRequestModel();
  constructor(private http: HttpClient, private router: Router) { }

  do(): void {
    this.http.post('http://localhost:8080/users/register', this.registerRequest).subscribe();
    this.router.navigateByUrl('/login');
  }
  ngOnInit() {
  }

}
