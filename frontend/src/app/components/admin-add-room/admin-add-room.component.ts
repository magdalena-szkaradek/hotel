import { Component, OnInit } from '@angular/core';
import { RoomService } from 'src/app/services/room.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-room',
  templateUrl: './admin-add-room.component.html',
  styleUrls: ['./admin-add-room.component.css']
})



export class AdminAddRoomComponent implements OnInit {

  successMsg: boolean = false;
  tooSmallRoomsValue: boolean = false;
  tooBigRoomsValue: boolean = false;


  constructor(
    private roomService: RoomService,
    private router: Router
  ) {
  
   }

  ngOnInit() {
  }

  addARoom({value,valid}){

    if (valid){

    //   this.userService.register(value).subscribe(res => {   
         

    //     this.successMsg = true;
    //     setTimeout(function() {
    //       this.successMsg = false;
    //     }.bind(this),2000);

    //     this.userService.getUsers().subscribe(users => {
    //       this.userService.usersBS.next(users);
    //     })
    //   },
    //   error => {
    //     this.userExists = true;
    //     setTimeout(function() {
    //       this.userExists = false;
    //     }.bind(this),2000);
    //     console.log("An error occured");       
    // }
    //   );

    if(value.beds < 1){
      console.log("Value can't be lessthan 1");

      this.tooSmallRoomsValue = true;

      setTimeout(function() {
        this.tooSmallRoomsValue = false;
      }.bind(this),2000);

    }else if(value.beds > 4){
      console.log("Value can't be grater than 4");

      this.tooBigRoomsValue = true;

      setTimeout(function() {
        this.tooBigRoomsValue = false;
      }.bind(this),2000);
    }else{
      console.log("This is a proper value");
      this.roomService.addRoom(value).subscribe(res => {

        this.successMsg = true;
        setTimeout(function() {
          this.successMsg = false;
        }.bind(this),2000);

      this.roomService.getRooms().subscribe(rooms => {
        this.roomService.roomsBS.next(rooms);
      })

    })
    }
   
    }else{
      console.log('Form is not valid');
    }
  }

}
