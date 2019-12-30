import { FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.scss']
})
export class AccountInfoComponent implements OnInit {

  avatar;
  isShowAccount = true ;
  isShowPostList;
  isShowPost;
  isShowCollections: boolean;
  username: any;
  provider: any;
  email: any;
  public files: any[];
  gender: string;
  startDate: Date;
  userInfo: any;

  constructor(private fb: FormBuilder) {
     const info = JSON.parse(localStorage.getItem('loginInfo'));
     this.avatar = info ? info.photoUrl : '../../../../../assets/images/login.png';
     this.username = info.name;
     this.provider = info.provider;
     this.email = info.email;
     this.gender = 'true' ;
     this.startDate = new Date(1997, 10, 19);
     this.userInfo = this.fb.group({
      username: [ info.name, Validators.required],
      dob: [ this.startDate, Validators.required],
      email: [info.email, Validators.required],
      gender: ['true'],
      job: ['Software Enginier'],
      phone: ['0975922740']
     });
  }
  ngOnInit() {
  }

  changeAvatar() {
  const element: HTMLElement = document.querySelector('input[type="file"]') as HTMLElement;
  element.click();
}

  onShowAccount() {
    this.isShowAccount = true;
    this.isShowPost = false;
    this.isShowCollections = false;
  }

  onShowPostList() {
    this.isShowAccount = false;
    this.isShowPost = true;
    this.isShowCollections = false;
  }

  onShowCollection() {
    this.isShowAccount = false;
    this.isShowPost = false;
    this.isShowCollections = true;
  }

  onChangePassword() {
  }

  onFileChanged($event) {
    this.files = $event.target.files;
    const reader = new FileReader();
    reader.readAsDataURL(this.files[0]);
    reader.onload = (event) => {
      this.avatar = reader.result;
    };
  }

  onSubmitUserInfo() {
    console.info(this.userInfo.value);
  }
}
