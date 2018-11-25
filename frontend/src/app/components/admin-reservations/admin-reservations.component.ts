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

    if (localStorage.getItem("user") !== "\"admin\"") {
      this.router.navigateByUrl('');
    } 
    
    this.reservationService.getReservations().subscribe(reservations => {
      this.reservationService.reservationBS.next(reservations);
      this.reservations = this.reservationService.reservationBS;

    });
  }

  // deletePrice(id) {
  //   if (confirm('Confirm deletion')) {

  //       this.reservationService.deleteReservation(id).subscribe(res => {

  //       this.successMsg = true;
  //       setTimeout(function() {
  //           this.successMsg = false;
  //       }.bind(this),2000);

  //         this.reservationService.getAllReservations().subscribe(reservations => {
  //           this.reservationService.reservationsBS.next(reservations);
  //         })

  //       },
  //       error => {
  //         this.errorMsg = true;
  //         console.log("An error occured");       
  //     });
  //   }
  // }

}
