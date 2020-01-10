import { GraphQueryService } from './../../../services/graphql/graph-query.service';
import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  constructor(private afAuth: AuthenticationService,private graphql:GraphQueryService ) { }
  fullName = new FormControl();
  username = new FormControl();
  phone = new FormControl();
  dob = new FormControl();
  email = new FormControl();
  password = new FormControl();
  minDate = new Date(1900, 0, 1);
  maxDate = new Date(2002, 0, 1);

  ngOnInit() {
  }

  register() {
    // console.log(this.email.value, this.password.value);
    // this.afAuth.register(this.email.value, this.password.value).then(result => alert('Vui lòng kiểm tra email để kích hoạt đăng ký'));
    const registerResult = this.graphql.register(this.fullName.value,this.email.value,this.password.value,this.phone.value,null);
    if(registerResult){
        alert('Đăng ký thành công. Kiểm tra email để kích hoạt tài khoản');
    }else {
      alert('Đăng ký thất bại ');
    }
  }
  onFullnameChange() {
    console.log(this.fullName.value);
  }
  onUsernameChange() {
    console.log(this.username.value);
  }
  onEmailChange() {
    console.log(this.email.value);
  }
  onPasswordChange() {
    console.log(this.password.value);
  }
  onPhoneChange() {
    console.log(this.phone.value);
  }
  onDobChange() {
    console.log(this.dob.value);
  }
}
