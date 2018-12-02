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

      this.roomService.availableRooms(merged).subscribe(res =>{
        this.availRooms = res.roomList;

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
}
