import { Component, OnInit } from '@angular/core';
import { RoomService } from '../services/room.service';


@Component({
  selector: 'app-search-component',
  templateUrl: './search-component.component.html',
  styleUrls: ['./search-component.component.css']
})
export class SearchComponentComponent implements OnInit {

  isTableShown: boolean = false;
  availRooms: any;
  numberOfAvailRoom: number = 0;
  Rooms = [];
  averageCosts = [];
  beginnigDate: Date;
  endingDate: Date;
 // allRooms: string;

  constructor(
    private roomService: RoomService
  ) {   
  }

  ngOnInit() {
  }
 
  searchForFreeRooms({value,valid}){
    let UserID = {
      "userId": localStorage.getItem("userID")
    }
    var merged = Object.assign(UserID, value);

    console.log(merged);
    if(valid){

      this.beginnigDate = value.startDate;
      this.endingDate = value.endDate;
      this.roomService.availableRooms(merged).subscribe(res =>{
        this.availRooms = res.roomList;

       // console.log("Avail rooms1: " + this.availRooms[0].name);

        this.roomService.getRooms().subscribe(rooms => {
          this.roomService.roomsBS.next(rooms);
        })

      })
    }else{
      console.log('Searching for available rooms went wrong');
    }
  }

  showTable(){
    this.isTableShown = true;
  }

  hideTable(){
    this.isTableShown = false;
  }
  addRoomReservation(id, i){
    // let UserID = {
    //   "userId": localStorage.getItem("userID")
    // }
    this.Rooms.push(this.availRooms[i].id);
    this.averageCosts.push(this.availRooms[i].pricePerNightWithSeasoningSystem);
    // var merged = Object.assign(UserID, value);
    // console.log("VALUE IS:" + id + " number: " + i);
    // console.log("Avail rooms: " + this.availRooms[i].name + localStorage.getItem("userID"));
    // console.log(this.availRooms[i].pricePerNightWithSeasoningSystem)
    // for(let entry of this.Rooms){
    //   console.log("AAAAAAAA: " + entry + "\n");
    // }
  //   for (var _i = 0; _i < this.Rooms.length; _i++) {
  //     var num = this.Rooms[_i];
  //     console.log("AAAAAAA: " + num + "\n");
  // }

  // for(let entry of this.averageCosts){
  //   console.log("BBBB: " + entry + "\n");
  // }
  
  }

  makeReservation(){
    let UserID = {
      "userId": localStorage.getItem("userID")
    }
    let resDates = {
      "startDate": this.beginnigDate,
      "endDate":this.endingDate,
    }

  //   for (var _i = 0; _i < this.Rooms.length; ++_i) {
  //     this.allRooms += this.Rooms[_i];
  // }
    let roomsToReserve = {
      "rooms": this.Rooms
    }

    let allCost = {
      "averageCosts": this.averageCosts
    }
   
    var merged = Object.assign(UserID, resDates);
    //console.log("Dates:" + this.beginnigDate + " " + this.endingDate)
    // console.log("I'm in here");
    // console.log(merged);

    var merged1 = Object.assign(merged, roomsToReserve);
    var merged2 = Object.assign(merged1, allCost);
    console.log(merged2);

    this.roomService.addReservation(merged2).subscribe(res =>{
      
    })

  }
}
