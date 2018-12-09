import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from 'src/app/services/user.service';
import {Http} from '@angular/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginFailed: boolean = false;
  public userRegistered: boolean = false;
  public isUserAnEmployee: boolean = false;

  constructor(
    private router: Router,
    private userService: UserService,
    private http: Http
  ) {
  }

  ngOnInit() {

    if (localStorage.getItem('user')) this.router.navigateByUrl('');

    if (localStorage.getItem('userRegistered')) {
      this.userRegistered = true;
      localStorage.removeItem('userRegistered');
    }
  }


  login({value, valid}) {
    if (valid) {

      this.userService.login(value).subscribe(res => {

        console.log(res);

        if (JSON.stringify(res) == 'null') {
          this.loginFailed = true;

          setTimeout(function () {
            this.loginFailed = false;
          }.bind(this), 4000);

        } else {

          let LoginResponse = res;
          let userResName = JSON.stringify(LoginResponse[0]);
          let isAnEmployeeRes = JSON.stringify(LoginResponse[1]);

          localStorage.setItem('userID', LoginResponse[2]);
          console.log('LOCALSTORAGE' + localStorage.getItem('userID'));

          console.log('aaa' + isAnEmployeeRes);
          if (isAnEmployeeRes == 'true') {
            this.isUserAnEmployee = true;
          }

          localStorage.setItem('user', userResName);
          if (localStorage.getItem('user') === '"admin"') {
            this.router.navigateByUrl('admin/clients');
          } else {

            console.log(this.isUserAnEmployee);
            if (this.isUserAnEmployee == true) {
              this.router.navigateByUrl('employeeProfile');
            } else {
              this.router.navigateByUrl('');
            }
          }

          console.log(userResName);
        }
      });
    } else {
      console.log('Form is not valid');
    }
  }
}
