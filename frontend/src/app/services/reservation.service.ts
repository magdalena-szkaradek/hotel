import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { BehaviorSubject } from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: Http) { }


  public reservationBS = new BehaviorSubject<string>(null);

  getReservations(){
    return this.http.get('http://localhost:8090/reservation/getAll').pipe(map(res => res.json()))
   }

   deleteReservation(id) {
    return this.http.delete('http://localhost:8090/reservation/delete/' + id).pipe(map(res => res.text()));
    }

  //  addReservation(reservation){
  //   return this.http.post('http://localhost:8090/reservation/add', reservation).pipe(map(res => res.json()))
  // }

}

