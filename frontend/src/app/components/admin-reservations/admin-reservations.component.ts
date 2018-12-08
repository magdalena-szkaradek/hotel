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
  date: any;



  constructor( 
    private router: Router,
    private reservationService: ReservationService
    ) { }

  ngOnInit() {

    this.reservationService.getReservations().subscribe(reservations => {
      this.reservationService.reservationBS.next(reservations);
      this.reservations = this.reservationService.reservationBS;
      this.date = new Date().toISOString();
    });
  }

  deleteReservation(id: any) {
    this.reservationService.deleteReservation(id).subscribe();
  }
}
