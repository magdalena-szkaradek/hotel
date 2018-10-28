import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  register({value,valid}){
    if (valid){

      this.userService.register(value).subscribe(res => {

        this.router.navigateByUrl('login');
        //check if user exists
      });
    }else{
      console.log('Form is not valid');
    }
  }

}
