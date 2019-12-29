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
  constructor() {
     const info = JSON.parse(localStorage.getItem('loginInfo'));
     this.avatar = info.photoUrl;
     this.username = info.name;
     this.provider = info.provider;
     this.email = info.email;
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
}
