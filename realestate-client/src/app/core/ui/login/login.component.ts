import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'angularx-social-login';
import { SocialUser } from 'angularx-social-login';
import { GoogleLoginProvider, FacebookLoginProvider, LinkedInLoginProvider } from 'angularx-social-login';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username = new FormControl();
  password = new FormControl();
  user: SocialUser;
  loginResult=false;
  // constructor(private authService: AuthService) { }
  ngOnInit(): void {
    // this.authService.authState.subscribe((user) => {
    //   this.user = user;
    //   console.log(user);
    // });
  }

  onLogIn(){
    if (this.username.value === 'danhthanh418' && this.password.value==='lovemy4ever') {
     this.loginResult=true;
     console.log(this.loginResult);
    }
    this.loginResult= false;
  }
  onUsernameChange() {
    console.log(this.username.value);
  }
  onPasswordChange() {
    console.log(this.password.value);
  }
  // signInWithGoogle(): void {
  //   this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(x => console.log(x));
  // }

  // signInWithFB(): void {
  //   this.authService.signIn(FacebookLoginProvider.PROVIDER_ID).then(x => console.log(x));
  // }

  // signInWithLinkedIn(): void {
  //   this.authService.signIn(LinkedInLoginProvider.PROVIDER_ID).then(x => console.log(x));
  // }

  // signOut(): void {
  //   this.authService.signOut();
  // }
}
