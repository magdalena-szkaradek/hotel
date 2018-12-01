import { Component, OnInit } from '@angular/core';
import { RoomService } from '../services/room.service';


@Component({
  selector: 'app-search-component',
  templateUrl: './search-component.component.html',
  styleUrls: ['./search-component.component.css']
})
export class SearchComponentComponent implements OnInit {

  availRooms: any;
  constructor(
    private roomService: RoomService
  ) {   
  }

  ngOnInit() {
  }
 

  searchForFreeRooms({value,valid}){
    
    console.log(value);

    // if(valid){

    //   this.roomService.availableRooms(value).subscribe(res =>{


    //     this.roomService.getRooms().subscribe(rooms => {
    //       this.roomService.roomsBS.next(rooms);
    //     })

    //   })
    // }else{
    //   console.log('Searching for available rooms went wrong');
    // }
  }
}
