import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-client',
  templateUrl: './admin-add-client.component.html',
  styleUrls: ['./admin-add-client.component.css']
})
export class AdminAddClientComponent implements OnInit {

  userExists: boolean = false;
  successMsg: boolean = false;

  constructor(

    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  addUserOrEmployee({value,valid}){

    if (valid){

      this.userService.register(value).subscribe(res => {   
         

        this.successMsg = true;
        setTimeout(function() {
          this.successMsg = false;
        }.bind(this),2000);

        this.userService.getUsers().subscribe(users => {
          this.userService.usersBS.next(users);
        })
      },
      error => {
        this.userExists = true;
        setTimeout(function() {
          this.userExists = false;
        }.bind(this),2000);
        console.log("An error occured");       
    }
      );
    }else{
      console.log('Form is not valid');
    }
  }

}


