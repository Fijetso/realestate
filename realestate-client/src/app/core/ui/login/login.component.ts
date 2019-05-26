import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
// import translate from 'google-translate-api';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  // username = new FormControl();
  // password = new FormControl();
  // email = new FormControl();
  isLogedIn = false;
  userInfo: any;
  loginInfo: any;
  loginForm: FormGroup;
  data: any;
  loginError: any;
  constructor(private authService: AuthenticationService) {
    this.userInfo = {
      email: '',
      password: ''
    };
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.maxLength(60)]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }
  ngOnInit(): void {
    this.getUserLogin();
  }
  getUserLogin() {
    this.data = this.authService.getUserLogin();
    console.log(this.data);
  }

  onLogIn(email: string, password: string) {
   this.authService
      .loginWithEmailPassWord(email, password).then(user => {console.log(user.providerData[0]),
                                                             this.authService.writeUserInfor();
                                                             this.isLogedIn = true;
                                                             this.loginError = false;
        }
      ).catch(error => {
        this.isLogedIn = false;
        this.loginError = true;
      });
  }
  loginWithGoogle() {
    this.authService.loginWithGoogle().then(data => {
      this.isLogedIn = true;
      this.authService.writeUserInfor();
      console.log(JSON.parse(localStorage.getItem('userInfor')));
    });
  }
  loginWithFacebook() {
    this.authService.loginWithFacebook();
  }
  onLogOut() {
    this.authService.logOut();
    localStorage.setItem('userInfor', null);
    this.isLogedIn = false;
  }
  onSubmitLogin(formValue) {
    // console.log(formValue);
    // this.loginInfo = formValue;
    // console.log( this.loginInfo);
    this.onLogIn(formValue.email, formValue.password);
  }
}
