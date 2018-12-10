import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  get front() {
    if (localStorage.getItem('user') === '"admin"') {
      return false;
    }
    return true;
  }

  isEmployee() {
    return localStorage.getItem('role') === 'employee';
  }
}
