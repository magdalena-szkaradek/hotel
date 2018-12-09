import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-component',
  templateUrl: './main-component.component.html',
  styleUrls: ['./main-component.component.css']
})
export class MainComponentComponent implements OnInit {
  isClient: boolean;
  isEmployee: boolean;

  get userLoggedIn() {
    if (localStorage.getItem('user')) {
      return true;
    }
    return false;
}
  constructor(private router: Router) { }

  ngOnInit() {
    this.isClient = localStorage.getItem('role') === 'user';
    this.isEmployee = localStorage.getItem('role') === 'employee';
  }

  login(){
    this.router.navigateByUrl('login');
  }

}
