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
  private socialUser: SocialUser;
  private loggedIn: boolean;
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
    this.authService.authState.subscribe(user => {
      this.socialUser = user;
      // console.log(this.socialUser);
      this.isLogedIn = this.loggedIn =  (user != null);
      if (this.loggedIn) {
        localStorage.setItem('loginInfo', JSON.stringify(user));
      }
    });
    this.isLogedIn  = this.loggedIn = JSON.parse(localStorage.getItem('loginInfo')) != null;
  }
  onLogIn(email: string, password: string) {
    this.login = this.graphql.login(email, password);
    const token = localStorage.getItem('login');
    if (token) {
      // console.log('getToken', token);
      this.graphql.getLoginInfo(token).subscribe(res => {
        this.data = res;
        // console.log('login infor',res);
        this.toastr.success('Đăng nhập bằng email thành công', 'Đăng nhập');
        this.isLogedIn = true;
        localStorage.setItem('loginInfo', JSON.stringify(res));
      });
    } else {
      this.toastr.error('Đăng nhập bằng email thất bại', 'Đăng nhập');
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
      console.log(JSON.parse(cookieResult)); this.toastr.success(
          JSON.parse(cookieResult).name,
          'Đăng nhập thành công'
        );
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
    }).catch(reason => {
      this.toastr.error(reason, 'Đăng nhập thất bại');
    });
  }
  onLogOut() {
    localStorage.setItem('loginInfo', null);
    this.data = null;
    this.isLogedIn = false;
    this.graphql.logout().subscribe(
      res => {
        // tslint:disable-next-line: no-console
        console.log('logout successful', res), localStorage.clear();
        this.isLogedIn = false;
      },
      error => {
        console.error(error);
      }
    );
    this.authService.signOut().then(
      res => {
        this.toastr.success('Đăng xuất thành công', 'Đăng xuất');
        localStorage.clear();
        this.cookie.deleteAll();
      }
    );
    // this.myAuthService
    //   .logOut()
    //   .then(res => {
    //     localStorage.clear();
    //     this.cookie.deleteAll();
    //   })
    //   .catch(err => {
    //     console.error(err);
    //   });
  }
  onSubmitLogin(formValue) {
    this.onLogIn(formValue.email, formValue.password);
  }
}
