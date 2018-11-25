import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-employees',
  templateUrl: './admin-employees.component.html',
  styleUrls: ['./admin-employees.component.css']
})
export class AdminEmployeesComponent implements OnInit {

  employees: any;
  successMsg: boolean = false;
  errorMsg: boolean = false;



  constructor( 
    private router: Router,
    private userService: UserService
    ) { }

  ngOnInit() {

    if (localStorage.getItem("user") !== "\"admin\"") {
      this.router.navigateByUrl('');
    } 
    
    this.userService.getEmployees().subscribe(employees => {
      this.userService.usersBS.next(employees);
      this.employees = this.userService.usersBS;

    });
  }

  deleteUser(user_id) {
    if (confirm('Confirm deletion')) {

        this.userService.deleteUser(user_id).subscribe(res => {

        this.successMsg = true;
        setTimeout(function() {
            this.successMsg = false;
        }.bind(this),2000);

          this.userService.getUsers().subscribe(users => {
            this.userService.usersBS.next(users);
          })

        },
        error => {
          this.errorMsg = true;
          console.log("An error occured");       
      });
    }
  }

}
