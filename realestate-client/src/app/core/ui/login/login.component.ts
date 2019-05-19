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
  constructor(private  authService: AuthenticationService) { }
  ngOnInit(): void {
  }

  onLogIn(){
    if (this.username.value === 'danhthanh418' && this.password.value === 'lovemy4ever') {
     this.loginResult = true;
     console.log(this.loginResult);
    }
    this.loginResult = false;
  }
  onUsernameChange() {
    console.log(this.username.value);
  }
  onPasswordChange() {
    console.log(this.password.value);
  }
  loginWithGoogle() {
    const result = this.authService.loginWithGoogle();
    console.log(result);
  }
  loginWithFacebook() {
    const result = this.authService.loginWithFacebook();
    console.log(result);
  }
}
