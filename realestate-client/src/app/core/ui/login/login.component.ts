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
  data:any;
  loginError:any;
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
        localStorage.setItem('userInfor', JSON.stringify(user.providerData[0]));
                                                            //  alert('Đăng nhập thành công');
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
    localStorage.removeItem('userInfor');
    this.isLogedIn = false;
  }
  onSubmitLogin(formValue) {
    // console.log(formValue);
    // this.loginInfo = formValue;
    // console.log( this.loginInfo);
    this.onLogIn(formValue.email, formValue.password);
  }
}
