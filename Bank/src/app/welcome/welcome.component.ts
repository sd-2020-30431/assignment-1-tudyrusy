import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {UserModel} from './user.model';
import {HttpHeaders} from '@angular/common/http';
import {ItemModel} from './item.model';
import {ItemModel1} from './item1.model';
import {CdModel} from './cd.model';
import {StringObj} from './stringObj.model';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})

export class WelcomeComponent implements OnInit {
  actualToken: string;
  userModel: UserModel = new UserModel();
  admin = false;
  item;
  waste;
  stringObj: StringObj = new StringObj();
  itemModel: ItemModel = new ItemModel();
  itemsModel: ItemModel1[];
  cdModel: CdModel = new CdModel();
  goal: number;
  s = 0;
  calWasted = 0;

  constructor(private http: HttpClient, private router: Router) {
  }

  add() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.http.post('http://localhost:8080/users/addItem', this.itemModel, httpOptions).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.log(error);
        alert('Error');
      }
    );
    setTimeout(() => {
      this.getItems();
    }, 2000);
  }

  viewProfile() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.http.get<UserModel>('http://localhost:8080/users/viewProfile', httpOptions).subscribe(
      newUser => {
        this.userModel = newUser;
        console.table(this.userModel);
      },
      error => console.log(error)
    );
    setTimeout(() => {
      this.getWaste();
    }, 1500);
  }

  getItems() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: window.localStorage.getItem('token')
      })
    };
    this.http.get<ItemModel1[]>('http://localhost:8080/users/getItems',
      httpOptions).subscribe(result => {
        this.itemsModel = result;
        console.table(this.itemsModel);
      },
      error => console.log(error));
    setTimeout(() => {
      this.getWaste();
    }, 1500);
  }

  logout() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.http.get('http://localhost:8080/users/logout', httpOptions).subscribe(result => console.log(result), error => console.log(error));
    this.router.navigateByUrl('/login');
  }

  ngOnInit() {
    this.actualToken = window.localStorage.getItem('token');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.http.get('http://localhost:8080/users/getRole', httpOptions).subscribe(result => {
      console.log(result);
      if (result.toString() === 'ADMIN') {
        this.admin = true;
      }
    }, error => console.log(error));
    this.viewProfile();
    this.getItems();
  }

  cd() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.item = (document.getElementById('item')) as HTMLSelectElement;
    this.cdModel.name = this.item.options[this.item.selectedIndex].text.toString();
    this.http.post('http://localhost:8080/users/setConsumption', this.cdModel,
      httpOptions).subscribe(
      result => {
        console.log(result);
      },
      error => {
        console.log(error);
        alert('Error');
      }
    );
    setTimeout(() => {
      this.getItems();
    }, 2000);
  }

  setGoal() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: this.actualToken
      })
    };
    this.http.post('http://localhost:8080/users/setGoal', this.goal,
      httpOptions).subscribe(
      result => {
        console.log(result);
      },
      error => {
        console.log(error);
        alert('Error');
      }
    );
    setTimeout(() => {
      this.viewProfile();
    }, 1500);
    setTimeout(() => {
      this.getWaste();
    }, 1500);
  }

  getWaste() {
    this.s = 0;
    for (const i of this.itemsModel) {
      if (i.consumptionDate === 'N/As') {
        this.s += i.perDay;
      }
    }
    this.calWasted = this.s - this.userModel.goal;
  }

  getWReport() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: window.localStorage.getItem('token')
      })
    };
    this.http.get<StringObj>('http://localhost:8080/users/getWeeklyReport',
      httpOptions).subscribe(result => {
        this.stringObj = result;
        console.table(this.stringObj);
      },
      error => console.log(error));
  }

  getMReport() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: window.localStorage.getItem('token')
      })
    };
    this.http.get<StringObj>('http://localhost:8080/users/getMonthlyReport',
      httpOptions).subscribe(result => {
        this.stringObj = result;
        console.table(this.stringObj);
      },
      error => console.log(error));
  }
}
