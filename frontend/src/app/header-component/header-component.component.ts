import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-component',
  templateUrl: './header-component.component.html',
  styleUrls: ['./header-component.component.css']
})
export class HeaderComponentComponent implements OnInit {

  user: string;

  get userLoggedIn(){
    if(localStorage.getItem("user")){ //check if user is logged in
      this.user = localStorage.getItem("user");
      return true;
    }
    return false;
}


  constructor() { }

  ngOnInit() {
  }

}
