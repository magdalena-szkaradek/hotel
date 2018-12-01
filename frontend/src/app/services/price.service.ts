import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { BehaviorSubject } from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PricesService {

  constructor(private http: Http) { }


  public pricesBS = new BehaviorSubject<string>(null);

  getPrices(){
    return this.http.get('http://localhost:8090/seasonPrice/getAll').pipe(map(res => res.json()))
   
   }

   deletePrice(id){
    return this.http.delete('http://localhost:8090/seasonPrice/delete/' + id).pipe(map(res => res.text()))
   }

   addAPrice(seasonPrice){
    return this.http.post('http://localhost:8090/seasonPrice/add', seasonPrice).pipe(map(res => res.json()))
  }

}

