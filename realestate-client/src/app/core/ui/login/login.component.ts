import { GraphQueryService } from './../../../services/graphql/graph-query.service';
import { User } from './../../../model/user/user';
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
  user: User;
  login: any;
  allTrade: any[];
  constructor(private authService: AuthenticationService, private graphql: GraphQueryService) {
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
    // firebase.auth().onAuthStateChanged(user => {
    //   if (user) {
    //     this.data = user.providerData[0];
    //     this.isLogedIn = true;
    //   } else {
    //     this.data = null;
    //     this.isLogedIn = false;
    //   }
    // });
  }

  onLogIn(email: string, password: string) {
    this.login = this.graphql.login(email, password);
    const token = localStorage.getItem('login');
    if (token) {
      console.info('getToken', token)
      this.graphql.getLoginInfo(token).subscribe((res) => {
        this.data = res;
        // console.info('login infor',res);
        this.isLogedIn = true;
      })
    }
    this.allTrade = this.graphql.getAllTrade();
  }

  getUserInfoSocial(datasource) {
    if (datasource) {
      this.data = datasource;
      this.isLogedIn = true;
    } else {
      this.data = null;
      this.isLogedIn = false;
    }
  }
  loginWithGoogle() {
    this.authService.loginWithGoogle().then(data => {
      this.isLogedIn = true;
      this.authService.writeUserInfor();
      this.getUserInfoSocial(data);
      // this.getUserLogin();
      // console.log(JSON.parse(localStorage.getItem('userInfor')));
    });
  }

  loginWithFacebook() {
    this.authService.loginWithFacebook().then(data => {
      this.isLogedIn = true;
      this.authService.writeUserInfor();
      this.getUserLogin();
      console.log(JSON.parse(localStorage.getItem('userInfor')));
    });
  }
  onLogOut() {
    // this.authService.logOut();
    // localStorage.setItem('userInfor', null);
    // this.data = null;
    // this.isLogedIn = false;
    this.graphql.logout().subscribe(res => {
      console.info('logout successful', res),
        localStorage.clear();
      this.isLogedIn = false;
    }, error => { console.error(error) });
  }
  onSubmitLogin(formValue) {
    this.onLogIn(formValue.email, formValue.password);
  }
}
