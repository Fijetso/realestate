import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { GraphQueryService } from './../../../services/graphql/graph-query.service';
import { User } from './../../../model/user/user';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from 'angularx-social-login';
import { SocialUser } from 'angularx-social-login';
import { DataService } from './../../../services/data/data.service';
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
  loginError: any;
  user: User;
  login: any;
  allTrade: any[];
  constructor(
    private myAuthService: AuthenticationService,
    private graphql: GraphQueryService,
    private cookie: CookieService,
    private toastr: ToastrService,
    private authService: AuthService,
    private data: DataService
  ) {
    this.userInfo = {
      email: '',
      password: ''
    };

    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6)
      ])
    });
  }

  ngOnInit(): void {
    this.data.currentLogin.subscribe(isLogedIn => {
      this.isLogedIn = isLogedIn;
      console.log(this.isLogedIn);
    });
  }
  onLogIn(email: string, password: string) {
    this.graphql.login(email, password);
    const token = localStorage.getItem('token');
    if (token) {
      this.graphql.getLoginInfo(JSON.parse(token)).subscribe(loginInfo => {
      this.data.changeCurrentUser(loginInfo);
      localStorage.setItem('loginInfo', JSON.stringify(loginInfo));
      localStorage.setItem('isLogedIn', 'true');
      this.data.changeLoginState(loginInfo != null);
      this.toastr.success('Đăng nhập thành công', 'Đăng nhập');
      });
    } else {
      this.toastr.error('Đăng nhập thất bại', 'Đăng nhập');
    }
  }
  loginWithGoogle() {
    this.myAuthService.loginWithGoogle().then(user => {
      console.log(user);
      localStorage.setItem('loginInfo', JSON.stringify(user));
      this.isLogedIn = true;
      const loginGoogle = localStorage.getItem('loginInfo');
      this.cookie.set('loginInfo', loginGoogle);
      const cookieResult = this.cookie.get('loginInfo');
      console.log(JSON.parse(cookieResult));
      this.toastr.success(
          JSON.parse(cookieResult).name,
          'Đăng nhập thành công'
        );
      // this.data.changeCurrentUser(user);
    }).catch(reason => {
      this.toastr.error(reason, 'Đăng nhập thất bại');
    });
  }

  loginWithFacebook() {
    this.myAuthService.loginWithFacebook().then(user => {
      console.log(user);
      localStorage.setItem('loginInfo', JSON.stringify(user));
      this.isLogedIn = true;
      const loginFacebook = localStorage.getItem('loginInfo');
      this.cookie.set('loginInfo', loginFacebook);

      const cookieResult = this.cookie.get('loginInfo');
      console.log(JSON.parse(cookieResult));
      this.toastr.success(
        JSON.parse(cookieResult).name,
        'Đăng nhập bằng Facebook thành công'
      );
      // this.data.changeCurrentUser(user);
    }).catch(reason => {
      console.log(reason);
    });
  }
  onLogOut() {
    localStorage.setItem('loginInfo', null);
    this.data = null;
    this.isLogedIn = false;
    this.graphql.logout().subscribe(
      res => {
        localStorage.clear();
        this.data.changeCurrentUser(null);
        localStorage.setItem('loginInfo',null);
        localStorage.setItem('isLogedIn', 'false');
        this.data.changeLoginState(false);
      },
      error => {
        console.error(error);
      }
    );
    this.authService.signOut().then(
      res => {
        this.toastr.success('Đăng xuất thành công', 'Đăng xuất');
        localStorage.clear();
      }
    );
  }
  onSubmitLogin(formValue) {
    this.onLogIn(formValue.email, formValue.password);
  }
}
