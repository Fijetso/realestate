import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'firebase';
import { Observable } from 'rxjs';
import { AuthService, GoogleLoginProvider, FacebookLoginProvider } from 'angularx-social-login';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(public router: Router, public authService: AuthService ) {
    this.getUserLogin();
  }
  get isLoggedIn(): boolean {
    const user = JSON.parse(localStorage.getItem('loginInfo'));
    return user !== null;
  }
  user: Observable<User>;
  updateProfile(profile: any): any {
    this.afAuth.auth.currentUser.updateProfile({
      displayName: profile.displayName,
      photoURL: profile.photoURL
    }).then(success => {
      alert(JSON.stringify(profile) );
      return true;
    }).catch(error => false);
  }
  async getUserLogin() {
    return await JSON.parse(localStorage.getItem('loginInfo'));
  }
  async logOut() {
    return await this.authService.signOut();
  }
  async loginWithGoogle() {
    return await this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }
  async loginWithFacebook() {
    return await this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
  }
}

