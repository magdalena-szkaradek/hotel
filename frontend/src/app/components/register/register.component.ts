import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userExists: boolean = false;

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    if (localStorage.getItem("user")) this.router.navigateByUrl('');
  }

  register({value,valid}){

    if (valid){

      this.userService.register(value).subscribe(res => {   
          localStorage.setItem("userRegistered", "true");
          this.router.navigateByUrl('login');
      },
      error => {
        this.userExists = true;
        console.log("An error occured");       
    }
      );
    }else{
      console.log('Form is not valid');
    }
  }

}
