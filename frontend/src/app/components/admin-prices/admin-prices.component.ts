import { Component, OnInit } from '@angular/core';
import { PricesService } from 'src/app/services/price.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-prices',
  templateUrl: './admin-prices.component.html',
  styleUrls: ['./admin-prices.component.css']
})
export class AdminPricesComponent implements OnInit {

  prices: any;
  successMsg: boolean = false;
  errorMsg: boolean = false;



  constructor( 
    private router: Router,
    private priceService: PricesService
    ) { }

  ngOnInit() {

    if (localStorage.getItem("user") !== "\"admin\"") {
      this.router.navigateByUrl('');
    } 
    
    this.priceService.getPrices().subscribe(prices => {
      this.priceService.pricesBS.next(prices);
      this.prices = this.priceService.pricesBS;

    });
  }

  deletePrice(price_id) {
    if (confirm('Confirm deletion')) {

        this.priceService.deletePrice(price_id).subscribe(res => {

        this.successMsg = true;
        setTimeout(function() {
            this.successMsg = false;
        }.bind(this),2000);

          this.priceService.getPrices().subscribe(prices => {
            this.priceService.pricesBS.next(prices);
          })

        },
        error => {
          this.errorMsg = true;
          console.log("An error occured");       
      });
    }
  }

}
