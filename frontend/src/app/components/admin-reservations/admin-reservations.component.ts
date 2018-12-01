import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-admin-reservations',
  templateUrl: './admin-reservations.component.html',
  styleUrls: ['./admin-reservations.component.css']
})
export class AdminReservationsComponent implements OnInit {

  reservations: any;
  successMsg: boolean = false;
  errorMsg: boolean = false;



  constructor( 
    private router: Router,
    private reservationService: ReservationService
    ) { }

  ngOnInit() {

    this.reservationService.getReservations().subscribe(reservations => {
      this.reservationService.reservationBS.next(reservations);
      this.reservations = this.reservationService.reservationBS;

    });
  }

  deleteReservation(id: any) {
    this.reservationService.deleteReservation(id).subscribe();
  }
}
