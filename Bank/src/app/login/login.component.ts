import {Component, OnInit} from '@angular/core';
import {LoginRequestModel} from './login.request.model';
import {LoginResponseModel} from './login.response.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {TokenModel} from './token.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  tokenModel: TokenModel = new TokenModel();
  loginRequest: LoginRequestModel = new LoginRequestModel();

  constructor(private http: HttpClient, private router: Router) {
  }

  login(): Observable<LoginResponseModel> {
    return this.http.post<LoginResponseModel>('http://localhost:8080/users/login', this.loginRequest);
  }

  do() {
    window.localStorage.removeItem('token');
    this.login().subscribe(result => {
        console.table(result);
        window.localStorage.setItem('token', result.token);
        console.log(result.token);
        this.router.navigateByUrl('/welcome');
      },
      error => {
        console.log(error);
      });

  }

  register(): void {
    this.router.navigateByUrl('/register');
  }

  ngOnInit() {
  }

}
