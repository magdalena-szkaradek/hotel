import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-employee-all-clients',
  templateUrl: './employee-all-clients.component.html',
  styleUrls: ['./employee-all-clients.component.css']
})
export class EmployeeAllClientsComponent implements OnInit {

  clients: any;
  successMsg: boolean = false;
  errorMsg: boolean = false;

  constructor(
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit() {

    this.userService.getClients().subscribe(clients => {
      this.userService.usersBS.next(clients);
      this.clients = this.userService.usersBS;
    })
  }


  deleteClient(user_id) {
    if (confirm('Confirm deletion')) {

        this.userService.deleteUser(user_id).subscribe(res => {

        this.successMsg = true;
        setTimeout(function() {
            this.successMsg = false;
        }.bind(this),2000);

          this.userService.getClients().subscribe(clients => {
            this.userService.usersBS.next(clients);
          })

        },
        error => {
          this.errorMsg = true;
          console.log("An error occured");       
      });
    }
  }

}
