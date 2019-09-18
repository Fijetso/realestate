import { ToastrService } from 'ngx-toastr';
import { ModalService } from 'src/app/services/modal.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import * as firebase from 'firebase';
// import translate from 'google-translate-api';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  isLogedIn = false;
  userInfo: any;
  loginInfo: any;
  loginForm: FormGroup;
  data: any;
  loginError: any;
  constructor(private authService: AuthenticationService, private modalService: ModalService, private toastr: ToastrService) {
    this.userInfo = {
      email: '',
      password: ''
    };
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }
  ngOnInit(): void {
    this.getUserLogin();
  }
  getUserLogin() {
    firebase.auth().onAuthStateChanged(user => {
        if (user) {
          this.data = user.providerData[0];
          this.isLogedIn = true;
        } else {
          this.data = null;
          this.isLogedIn = false;
        }
    });
  }

  onLogIn(email: string, password: string) {
   this.authService
      .loginWithEmailPassWord(email, password).then(user => {console.log(user.providerData[0]),
                                                             this.authService.writeUserInfor();
                                                             this.isLogedIn = true;
                                                             this.loginError = false;
                                                             this.getUserLogin();
                                                             this.modalService.dialog.closeAll();
                                                             this.toastr.success('Chào mừng bạn đã trở lại ' +
                                                             user.providerData[0].displayName.toString(), 'Đăng nhập');
                                                             location.reload();
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
      this.getUserLogin();
      console.log(JSON.parse(localStorage.getItem('userInfor')));
      this.toastr.success('Chào mừng bạn đã trở lại ' +
      data.user.providerData[0].displayName, 'Đăng nhập');
      location.reload();
    });
  }
  loginWithFacebook() {
    this.authService.loginWithFacebook().then(data => {
      this.isLogedIn = true;
      this.authService.writeUserInfor();
      this.getUserLogin();
      console.log(JSON.parse(localStorage.getItem('userInfor')));
      this.toastr.success('Chào mừng bạn đã trở lại ' +
      data.user.providerData[0].displayName, 'Đăng nhập');
      location.reload();
    });
  }
  onLogOut() {
    this.authService.logOut();
    localStorage.setItem('userInfor', null);
    this.data = null;
    this.isLogedIn = false;
    this.modalService.dialog.closeAll();
    this.toastr.info('Đăng xuất thành công ', 'Đăng xuất');
    location.reload();
  }
  onSubmitLogin(formValue) {
    this.onLogIn(formValue.email, formValue.password);
  }
}
