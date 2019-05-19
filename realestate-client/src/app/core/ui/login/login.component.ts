import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username = new FormControl();
  password = new FormControl();
  loginResult = false;
  userInfo: any;
  constructor(private authService: AuthenticationService) {}
  ngOnInit(): void {}

  onLogIn() {
    console.log(this.authService
      .loginWithEmailPassWord(this.username.value, this.password.value));
  }
  onUsernameChange() {
    console.log(this.username.value);
  }
  onPasswordChange() {
    console.log(this.password.value);
  }
  loginWithGoogle() {
    this.authService.loginWithGoogle().then(data => {
      // console.log(data.additionalUserInfo.profile);
      this.userInfo = data.additionalUserInfo.profile;
      console.log(this.userInfo, typeof this.userInfo);
      localStorage.setItem('userInfor', JSON.stringify(this.userInfo));
      console.log(JSON.parse(localStorage.getItem('userInfor')));
    });
  }
  loginWithFacebook() {
    this.authService.loginWithFacebook();
  }
  onLogOut() {
    this.authService.logOut();
  }
}
