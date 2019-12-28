import { ToastrService } from 'ngx-toastr';
import { GraphQueryService } from './../../../services/graphql/graph-query.service';
import { User } from './../../../model/user/user';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { CookieService } from 'ngx-cookie-service';
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
  user: User;
  login: any;
  allTrade: any[];
  constructor(
    private myAuthService: AuthenticationService,
    private graphql: GraphQueryService,
    private cookie: CookieService,
    private toastr: ToastrService
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
    this.getUserLogin();
  }

  getUserLogin() { }

  onLogIn(email: string, password: string) {
    this.login = this.graphql.login(email, password);
    const token = localStorage.getItem('login');
    if (token) {
      // tslint:disable-next-line: no-console
      console.info('getToken', token);
      this.graphql.getLoginInfo(token).subscribe(res => {
        this.data = res;
        // console.info('login infor',res);
        this.isLogedIn = true;
      });
    }
    this.allTrade = this.graphql.getAllTrade();
  }
  loginWithGoogle() {
    this.myAuthService.loginWithGoogle().then(user => {
      // tslint:disable-next-line: no-console
      console.info(user);
      localStorage.setItem('loginGoogle', JSON.stringify(user));
      this.isLogedIn = true;
      const loginGoogle = localStorage.getItem('loginGoogle');
      this.cookie.set('loginGoogle', loginGoogle);
      // tslint:disable-next-line: no-console
      const cookieResult = this.cookie.get('loginGoogle');
      // tslint:disable-next-line: no-console
      console.info(JSON.parse(cookieResult)); this.toastr.success(
          JSON.parse(cookieResult).name,
          'Đăng nhập thành công'
        );
    }).catch(reason => {
      this.toastr.error(reason, 'Đăng nhập thất bại');
    });
  }

  loginWithFacebook() {
    this.myAuthService.loginWithFacebook().then(user => {
      // tslint:disable-next-line: no-console
      console.info(user);
      localStorage.setItem('loginFacebook', JSON.stringify(user));
      this.isLogedIn = true;
      const loginFacebook = localStorage.getItem('loginFacebook');
      this.cookie.set('loginFacebook', loginFacebook);
      // tslint:disable-next-line: no-console
      const cookieResult = this.cookie.get('loginFacebook');
      // tslint:disable-next-line: no-console
      console.info(JSON.parse(cookieResult));
      this.toastr.success(
        JSON.parse(cookieResult).name,
        'Đăng nhập bằng Facebook thành công'
      );
    }).catch(reason => {
      this.toastr.error(reason, 'Đăng nhập thất bại');
    });
  }
  onLogOut() {
    localStorage.setItem('userInfor', null);
    this.data = null;
    this.isLogedIn = false;
    this.graphql.logout().subscribe(
      res => {
        // tslint:disable-next-line: no-console
        console.info('logout successful', res), localStorage.clear();
        this.isLogedIn = false;
      },
      error => {
        console.error(error);
      }
    );
    this.myAuthService
      .logOut()
      .then(res => {
        localStorage.clear();
        this.cookie.deleteAll();
      })
      .catch(err => {
        console.error(err);
      });
  }
  onSubmitLogin(formValue) {
    this.onLogIn(formValue.email, formValue.password);
  }
}
