import { ToastrService } from 'ngx-toastr';
import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  constructor(private afAuth: AuthenticationService,private toastr: ToastrService) { }
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
    console.log(this.email.value, this.password.value);
    this.afAuth.register(this.email.value, this.password.value).
    then(result => this.toastr.success('Đăng ký thành công .\r\nVui lòng kiểm tra email để kích hoạt đăng ký','Đăng ký tài khoản'))
    .catch(err => this.toastr.error('Đăng ký thất bại', 'Đăng ký tài khoản'));
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
