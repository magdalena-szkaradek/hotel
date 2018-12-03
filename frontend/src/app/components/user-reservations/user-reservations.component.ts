import {Component, OnInit} from '@angular/core';

import {Router} from '@angular/router';
import {ReservationService} from 'src/app/services/reservation.service';
import {Reservation} from './Reservation';

@Component({
  selector: 'app-admin-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.css']
})
export class UserReservationsComponent implements OnInit {

  reservations: Reservation[];
  successMsg: boolean = false;
  errorMsg: boolean = false;

  constructor(
    private router: Router,
    private reservationService: ReservationService
  ) {
  }

  ngOnInit() {
    const UserID = {
      'userId': localStorage.getItem('userID')
    };
    this.reservationService.getUserReservation(UserID.userId.toString()).subscribe(reservations => {
      this.reservations = reservations;
    });
  }

  deleteReservation(id: any) {
    this.reservationService.deleteReservation(id).subscribe();
  }
}
