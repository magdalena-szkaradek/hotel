import {Component, OnInit} from '@angular/core';

import {Router} from '@angular/router';
import {ReservationService} from 'src/app/services/reservation.service';
import {Reservation} from './Reservation';

@Component({
  selector: 'app-client-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.css']
  
})

export class UserReservationsComponent implements OnInit {

  reservations: Reservation[];
  successMsg: boolean = false;
  errorMsg: boolean = false;
  date: any;

  constructor(
    private router: Router,
    private reservationService: ReservationService
  ) { }

 
  ngOnInit() {
    const UserID = {
      'userId': localStorage.getItem('userID')
    };

    this.reservationService.getUserReservation(UserID.userId.toString()).subscribe(reservations => {
      this.reservations = reservations;
      this.date = new Date().toISOString();
     
  
    });
  
  }

  deleteReservation(id) {
    if (confirm('Confirm deletion')) {

      this.reservationService.deleteReservation(id).subscribe(res => {
      
      this.successMsg = true;
      setTimeout(function() {
          this.successMsg = false;
      }.bind(this),2000);

        const UserID = {
          'userId': localStorage.getItem('userID')
        };
        
        this.reservationService.getUserReservation(UserID.userId.toString()).subscribe(reservations => {
          this.reservations = reservations;
        })

      },
      error => {
        this.errorMsg = true;
        console.log("An error occured");       
    });
    }
  }
    
}


