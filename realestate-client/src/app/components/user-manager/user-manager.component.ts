import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-user-manager',
  templateUrl: './user-manager.component.html',
  styleUrls: ['./user-manager.component.scss']
})
export class UserManagerComponent implements OnInit {
  photoSrc: null;
  data = JSON.parse(localStorage.getItem('userInfor'));
  genderList = [
    {
      value: true,
      name: 'Nam'
    },
    {
      value: false,
      name: 'Nữ'
    },
  ];
  accountForm = null;
  changePassForm = null;
  notMatch = false;
  constructor(private auth: AuthenticationService, private toastr: ToastrService) {
    if (this.data) {
      this.accountForm = new FormGroup ({
        displayName : new FormControl(this.data.displayName),
        dob : new FormControl(),
        gender: new FormControl(true),
        email: new FormControl(this.data.email),
        phoneNumber: new FormControl(this.data.phoneNumber),
        photoURL: new FormControl(this.data.photoURL)
      });
      this.photoSrc = this.accountForm.value.photoURL;
    }
    this.changePassForm = new FormGroup({
      oldPassword : new FormControl(),
      newPassword: new FormControl( Validators.minLength(6)),
      reTypeNewPassword: new FormControl(Validators.minLength(6))
    });
  }

  ngOnInit() {
  }

  convertDate(date: any) {
    return new DatePipe('en-US').transform(new Date(date), 'mediumDate');
  }
  onSubmitAccountUpdate() {
     if ( this.auth.updateProfile(this.accountForm.value)) {
      this.toastr.success('Cập nhật thành công');
     } else {
      this.toastr.error('Cập nhật thất bại');
     }
  }
  onSubmitChangePass() {
    this.toastr.info(JSON.stringify(this.changePassForm.value), 'Thông tin mật khẩu');
    if (this.changePassForm.get('newPassword').value !== this.changePassForm.get('reTypeNewPassword').value){
      return this.notMatch = true;
    }
  }
  get oldPassword() {
    return this.changePassForm.get('oldPassword').value;
  }
  get newPassword() {
    return this.changePassForm.get('newPassword').value;
  }
  get reTypeNewPassword() {
    return this.changePassForm.get('newPassword').value;
  }
}
