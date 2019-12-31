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
  listPost: { id: number; title: string; content: string; imgPath: string; }[];
  collections: { id: number; title: string; content: string; imgPath: string; }[];
  constructor(private fb: FormBuilder) {
     const info = JSON.parse(localStorage.getItem('loginInfo'));
     this.avatar = info ? info.photoUrl : '../../../../../assets/images/login.png';
     this.username = info ? info.name : null;
     this.provider = info ? info.provider : null;
     this.email = info ? info.email : null;
     this.gender = 'true' ;
     this.startDate = new Date(1997, 10, 19);
     this.userInfo = this.fb.group({
      username: [info ? info.name : null , Validators.required],
      dob: [ this.startDate, Validators.required],
      email: [info ? info.email : null, Validators.required],
      gender: ['true'],
      job: ['Software Enginier'],
      phone: ['0975922740']
     });

     this.listPost = [
      {
        id: 1,
        title: 'Eu voluptate adipisicing velit est sit deserunt ea ex.',
        content: 'Ea nulla exercitation veniam sint magna consequat cupidatat amet mollit. Culpa ex est laborum irure in. Fugiat culpa commodo exercitation deserunt veniam tempor mollit aute duis culpa elit proident aliquip consectetur.',
        imgPath: 'https://via.placeholder.com/300'
      },
      {
        id: 2,
        title: 'Officia officia Lorem esse fugiat minim minim consequat sint aliqua ullamco qui aute.',
        content: 'Commodo dolor excepteur pariatur consectetur esse do deserunt magna reprehenderit sint qui voluptate. In et voluptate ex tempor velit non duis nostrud ipsum voluptate labore fugiat ullamco. Do dolor esse dolore do do. Voluptate ullamco consequat magna nostrud eiusmod.',
        imgPath: 'https://via.placeholder.com/300'
      },
      {
        id: 3,
        title: 'Sunt laborum laboris nostrud mollit nulla dolore anim eu velit Lorem aute dolore minim aute.',
        content: 'Pariatur eu et ipsum nisi. Anim esse incididunt irure reprehenderit sit. Sint elit laboris laborum laborum qui reprehenderit reprehenderit.',
        imgPath: 'https://via.placeholder.com/300'
      },
      {
        id: 4,
        title: 'Labore eiusmod in deserunt Lorem minim eiusmod nisi pariatur excepteur.',
        content: 'Ex exercitation deserunt in nulla incididunt incididunt aute ea enim qui nisi minim tempor esse. Officia consequat sint ad nulla esse minim cillum aliquip. Nostrud enim nostrud nulla exercitation. Nisi minim tempor veniam in incididunt anim. Occaecat duis esse velit excepteur. Minim occaecat ad pariatur ea sint elit culpa id qui cupidatat ipsum. Irure duis minim anim culpa irure fugiat et non.',
        imgPath: 'https://via.placeholder.com/300'
      }
     ];

     this.collections = [
       {
        id: 1,
        title: 'Labore eiusmod in deserunt Lorem minim eiusmod nisi pariatur excepteur.',
        content: 'Ex exercitation deserunt in nulla incididunt incididunt aute ea enim qui nisi minim tempor esse. Officia consequat sint ad nulla esse minim cillum aliquip. Nostrud enim nostrud nulla exercitation. Nisi minim tempor veniam in incididunt anim. Occaecat duis esse velit excepteur. Minim occaecat ad pariatur ea sint elit culpa id qui cupidatat ipsum. Irure duis minim anim culpa irure fugiat et non.',
        imgPath: 'https://via.placeholder.com/300'
       },
       {
        id: 2,
        title: 'Cillum reprehenderit aliquip est aliquip commodo tempor.',
        content: 'Aliqua ex irure adipisicing Lorem Lorem exercitation irure aliqua. Qui velit fugiat ea sint magna laboris officia occaecat ipsum duis velit reprehenderit consectetur. Minim excepteur ea veniam labore ea veniam. Nulla cillum officia adipisicing ut commodo tempor incididunt.',
        imgPath: 'https://via.placeholder.com/300'
       }
     ];
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
