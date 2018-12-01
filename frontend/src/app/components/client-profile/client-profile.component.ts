import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {

   id: string

  constructor() { }

  ngOnInit() {
    
  }

  get userLoggedIn(){
    if(localStorage.getItem("id")){ //check if user is logged in
      this.id = localStorage.getItem("id");
      return true;
    }
    return false;
}


}
