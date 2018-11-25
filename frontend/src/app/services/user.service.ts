import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import { BehaviorSubject } from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})


export class UserService {

  constructor(private http: Http) { }

  public usersBS = new BehaviorSubject<string>(null);

  register(user){
    return this.http.post('http://localhost:8090/user/addUser', user).pipe(map(res => res.json()))
  }

  login(user){
    return this.http.post('http://localhost:8090/user/login', user).pipe(map(res => res.json()))
  }

  getUsers(){
    return this.http.get('http://localhost:8090/user/getAllUsers').pipe(map(res => res.json()))
   
   }

   deleteUser(user_id){
    return this.http.delete('http://localhost:8090/user/delete/' + user_id).pipe(map(res => res.text()))
   }

   getClients(){
    return this.http.get('http://localhost:8090/user/getClients').pipe(map(res => res.json()))
   
   }
   getEmployees(){
    return this.http.get('http://localhost:8090/user/getEmployees').pipe(map(res => res.json()))
   
   }
}
