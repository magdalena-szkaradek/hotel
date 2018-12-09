import {Component, OnInit} from '@angular/core';
import {RoomService} from '../services/room.service';
import {UserService} from '../services/user.service';


class ClientDetails {
  user_id: number;
  nameAndSurname: string;
}

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
  clientsDetails: ClientDetails[];

  constructor(
    private roomService: RoomService,
    private userService: UserService
  ) {
  }

  ngOnInit() {
    this.userService.getClientDetails().subscribe(data => {this.clientsDetails = data; console.log(this.clientsDetails);});
  }

  searchForFreeRooms({value, valid}) {
    let UserID = {
      'userId': localStorage.getItem('userID')
    };
    var merged = Object.assign(UserID, value);

    console.log(merged);
    if (valid) {

      this.beginnigDate = value.startDate;
      this.endingDate = value.endDate;
      this.roomService.availableRooms(merged).subscribe(res => {
        this.availRooms = res.roomList;

        // console.log("Avail rooms1: " + this.availRooms[0].name);

        this.roomService.getRooms().subscribe(rooms => {
          this.roomService.roomsBS.next(rooms);
        });

      });
    } else {
      console.log('Searching for available rooms went wrong');
    }
  }

  showTable() {
    this.isTableShown = true;
  }

  hideTable() {
    this.isTableShown = false;
  }

  addRoomReservation(id, i) {
    this.Rooms.push(this.availRooms[i].id);
    this.averageCosts.push(this.availRooms[i].pricePerNightWithSeasoningSystem);
  }

  makeReservation() {
    let UserID = {
      'userId': localStorage.getItem('userID')
    };
    let resDates = {
      'startDate': this.beginnigDate,
      'endDate': this.endingDate,
    };
    let roomsToReserve = {
      'rooms': this.Rooms
    };

    let allCost = {
      'averageCosts': this.averageCosts
    };

    var merged = Object.assign(UserID, resDates);
    var merged1 = Object.assign(merged, roomsToReserve);
    var merged2 = Object.assign(merged1, allCost);
    console.log(merged2);

    this.roomService.addReservation(merged2).subscribe(res => {

    });

  }
}
