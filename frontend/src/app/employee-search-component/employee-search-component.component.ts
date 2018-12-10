import {Component, OnInit} from '@angular/core';
import {RoomService} from '../services/room.service';
import {UserService} from '../services/user.service';

class ClientDetails {
  userId: number;
  nameAndSurname: string;
}

@Component({
  selector: 'app-employee-search-component',
  templateUrl: './employee-search-component.component.html',
  styleUrls: ['./employee-search-component.component.css']
})
export class EmployeeSearchComponent implements OnInit {

  isTableShown: boolean = false;
  availRooms: any;
  numberOfAvailRoom: number = 0;
  Rooms = [];
  averageCosts = [];
  beginnigDate: Date;
  endingDate: Date;
  // allRooms: string;
  clientsDetails: ClientDetails[];
  selectedUser;
  userId: number;
  private successMsg: boolean;
  private errorMsg: boolean;

  constructor(
    private roomService: RoomService,
    private userService: UserService
  ) {
  }

  ngOnInit() {
    this.userService.getClientDetails().subscribe(data => {
      this.clientsDetails = data;
      console.log(this.clientsDetails);
    });
  }

  searchForFreeRooms({value, valid}) {
    this.Rooms = [];
    this.averageCosts = [];
    this.userId = value.userId;
    if (valid) {
      this.beginnigDate = value.startDate;
      this.endingDate = value.endDate;
      this.roomService.availableRooms(value).subscribe(res => {
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

  addRoomReservation(room) {
    this.Rooms.push(room.id);
    this.averageCosts.push(room.pricePerNightWithSeasoningSystem);
  }

  removeRoomFromReservation(room) {
    const roomIndex = this.Rooms.indexOf(room.id);
    const costIndex = this.averageCosts.indexOf(room.pricePerNightWithSeasoningSystem);
    if (roomIndex > -1) {
      this.Rooms.splice(roomIndex, 1);
    }
    if (costIndex > -1) {
      this.averageCosts.splice(costIndex, 1);
    }
  }

  makeReservation() {
    let UserID = {'userId': Number(this.userId)};
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
        this.successMsg = true;
        setTimeout(function () {
          this.successMsg = false;
        }.bind(this), 4000);
      },
      () => {
        this.errorMsg = true;
        console.log('An error occured');
      }
    );
    this.hideTable();
  }

  isRoomAdded(id: any) {
    return this.Rooms.includes(id);
  }
}
