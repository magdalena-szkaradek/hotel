import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-admin-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.css']
})
export class UserReservationsComponent implements OnInit {

  reservations: any;
  successMsg: boolean = false;
  errorMsg: boolean = false;

  constructor( 
    private router: Router,
    private reservationService: ReservationService
    ) { }
  
  ngOnInit() {
 
  //   this.reservationService.getUserReservation().subscribe(reservations => {
  //     this.reservationService.reservationBS.next(reservations);
  //     this.reservations = this.reservationService.reservationBS;

  //   });
  // }
  }

  deleteReservation(id: any) {
    this.reservationService.deleteReservation(id).subscribe();
  }
}
