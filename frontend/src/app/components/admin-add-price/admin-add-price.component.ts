import { Component, OnInit } from '@angular/core';
import { PricesService } from 'src/app/services/price.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-price',
  templateUrl: './admin-add-price.component.html',
  styleUrls: ['./admin-add-price.component.css']
})

export class AdminAddPricesComponent implements OnInit {

  successMsg: boolean = false;
  tooSmallRoomsValue: boolean = false;
  tooBigRoomsValue: boolean = false;


  constructor(
    private pricesService: PricesService,
    private router: Router
  ) {
  
   }

  ngOnInit() {
  }

  addAPrice({value,valid}){

    if (valid){

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
      this.pricesService.addAPrice(value).subscribe(res => {

        this.successMsg = true;
        setTimeout(function() {
          this.successMsg = false;
        }.bind(this),2000);

      this.pricesService.getPrices().subscribe(prices => {
        this.pricesService.pricesBS.next(prices);
      })

    })
    }
   
    }else{
      console.log('Form is not valid');
    }
  }

}
