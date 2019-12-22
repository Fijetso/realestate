import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { auth } from 'firebase/app';
import { AngularFireAuth } from '@angular/fire/auth';
import { User } from 'firebase';
import { Observable } from 'rxjs';
import * as firebase from 'firebase/app';
import { AuthService, GoogleLoginProvider, FacebookLoginProvider } from "angularx-social-login";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  headers: any;
  constructor(public router: Router, public authService : AuthService ) {
    this.getUserLogin();
  }
  get isLoggedIn(): boolean {
    const user = JSON.parse(localStorage.getItem('userInfor'));
    return user !== null;
  }
  user: Observable<User>;
  async getUserLogin() {
    return await JSON.parse(localStorage.getItem('userInfor'));
  }
  async logOut() {
    return await this.authService.signOut();
  }
  async loginWithGoogle() {
    return await this.authService.signIn(GoogleLoginProvider.PROVIDER_ID)
  }
  async loginWithFacebook() {
    return await this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
  }
}

