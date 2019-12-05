import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserRegistrationService } from '../service/registration/user-registration.service';

export class User {
  constructor(
    public firstName: string,
    public lastName: string,
    public panCard: string,
    public email: string,
    public username: string,
    public password: string
  ){  }
}

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user: User;

  constructor(private registrationService: UserRegistrationService, private router: Router) { }

  ngOnInit() {
    this.user = new User("", "", "", "", "", "");
  }

  saveUser() {
    this.registrationService.registerUser(this.user).subscribe (
      data => {
        this.router.navigate(['login'])
      })
  }

}
