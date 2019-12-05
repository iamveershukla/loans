import { Component, OnInit } from '@angular/core';
import { JwtAuthenticationService } from '../service/authentication/jwt-authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
    constructor(private jwtAuthenticationService: JwtAuthenticationService) { }

  ngOnInit() {
  }

}
