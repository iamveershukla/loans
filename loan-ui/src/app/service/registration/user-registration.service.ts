import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/registration/registration.component';
import { API_URL } from 'src/app/app.constants';

@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {

  constructor(private http: HttpClient) { }


    registerUser(user: User){
      return this.http.post(
                  `${API_URL}/users/signup`
                  , user);
    }







}
