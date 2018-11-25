import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-clients',
  templateUrl: './admin-clients.component.html',
  styleUrls: ['./admin-clients.component.css']
})
export class AdminClientsComponent implements OnInit {

  clients: any;
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
    
    this.userService.getClients().subscribe(clients => {
      this.userService.usersBS.next(clients);
      this.clients = this.userService.usersBS;

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
