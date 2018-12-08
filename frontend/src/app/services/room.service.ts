import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { BehaviorSubject } from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http: Http) { }


  public roomsBS = new BehaviorSubject<string>(null);

  getRooms(){
    return this.http.get('http://localhost:8090/room/getAll').pipe(map(res => res.json()))
   
   }

   deleteRoom(id){
    return this.http.delete('http://localhost:8090/room/delete/' + id).pipe(map(res => res.text()))
   }

   addRoom(room){
    return this.http.post('http://localhost:8090/room/add', room).pipe(map(res => res.json()))
  }

  availableRooms(dates){
    return this.http.post('http://localhost:8090/room/searchBy', dates).pipe(map(res => res.json()))
  }
  addReservation(info){
    return this.http.post('http://localhost:8090/reservation/add', info).pipe(map(res => res.json()));
   }

}
