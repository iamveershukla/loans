import { JwtAuthenticationService } from '../authentication/jwt-authentication.service';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterJwtAuthService implements HttpInterceptor{

  constructor(
    private jwtAuthenticationService : JwtAuthenticationService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler){

    let jwtAuthHeaderString = this.jwtAuthenticationService.getAuthenticatedToken();
    let username = this.jwtAuthenticationService.getAuthenticatedUser()

    if(jwtAuthHeaderString && username) { 
      request = request.clone({
        setHeaders : {
            Authorization : jwtAuthHeaderString
          }
        }) 
    }
    return next.handle(request);
  }


}
