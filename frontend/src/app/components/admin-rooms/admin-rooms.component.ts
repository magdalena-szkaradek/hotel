import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-admin-rooms',
  templateUrl: './admin-rooms.component.html',
  styleUrls: ['./admin-rooms.component.css']
})
export class AdminRoomsComponent implements OnInit {

  rooms: any;
  successMsg: boolean = false;
  errorMsg: boolean = false;

  constructor(
    private router: Router,
    private roomService: RoomService
  ) { }

  ngOnInit() {

    this.roomService.getRooms().subscribe(rooms => {
      this.roomService.roomsBS.next(rooms);
      this.rooms = this.roomService.roomsBS;
    })
  }


  deleteRoom(id) {
    if (confirm('Confirm deletion')) {

      this.roomService.deleteRoom(id).subscribe(res=> {

        this.successMsg = true;
        setTimeout(function() {
            this.successMsg = false;
        }.bind(this),2000);

        this.roomService.getRooms().subscribe(rooms => {
          this.roomService.roomsBS.next(rooms);
        })

      },
         error => {
          this.errorMsg = true;
          console.log("An error occured");       
      });
    }
  }

}
