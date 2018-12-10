import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  private isEmployee: boolean;

  get front() {
    if (localStorage.getItem('user') === '"admin"') {
      return false;
    }
    return true;
  }

  ngOnInit() {
    this.isEmployeeF();
  }

  isEmployeeF() {
    this.isEmployee = localStorage.getItem('role') === 'employee';
    return this.isEmployee;
  }
}
