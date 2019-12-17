import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { auth } from 'firebase/app';
import { AngularFireAuth } from '@angular/fire/auth';
import { User } from 'firebase';
import { Observable } from 'rxjs';
import * as firebase from 'firebase/app';
import 'firebase/auth';
import { HttpHeaders, HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  headers: any;
  constructor(public afAuth: AngularFireAuth, public router: Router, private http: HttpClient) {
    this.afAuth.authState.subscribe(user => {
        this.writeUserInfor();
    });
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
  async loginWithEmailPassWord(email: string, password: string) {
    const result = await this.afAuth.auth.signInWithEmailAndPassword(
      email,
      password
    );
    return result.user;
  }
  async sendEmailVerification() {
    await this.afAuth.auth.currentUser.sendEmailVerification();
    // this.router.navigate(['admin/verify-email']);
  }
  async register(email: string, password: string) {
    const result = await this.afAuth.auth.createUserWithEmailAndPassword(
      email,
      password
    );
    this.sendEmailVerification();
  }
  async sendPasswordResetEmail(passwordResetEmail: string) {
    return await this.afAuth.auth.sendPasswordResetEmail(passwordResetEmail);
  }
  async logOut() {
    return await this.afAuth.auth.signOut();
  }
  async loginWithGoogle() {
    return await this.afAuth.auth.signInWithPopup(
      new auth.GoogleAuthProvider()
    );
  }
  async loginWithFacebook() {
    return await this.afAuth.auth.signInWithPopup(
      new auth.FacebookAuthProvider()
    );
  }

  async writeUserInfor() {
    firebase.auth().onAuthStateChanged(
      user => {
        if (user) {
          localStorage.setItem('userInfor', JSON.stringify(user.providerData[0]));
        } else {
          localStorage.setItem('userInfor', null);
        }
      }
    );
  }
}

