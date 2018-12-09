import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-main-component',
  templateUrl: './main-component.component.html',
  styleUrls: ['./main-component.component.css']
})
export class MainComponentComponent implements OnInit {
  isClient: boolean;
  isEmployee: boolean;

  constructor() {}

  ngOnInit() {
    this.isClient = localStorage.getItem('role') === 'user';
    this.isEmployee = localStorage.getItem('role') === 'employee';
  }

}
