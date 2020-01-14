import { FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { DataService } from './../../../../services/data/data.service';
import { ApiService } from 'src/app/services/api/api.service';
import { Router } from '@angular/router';
import { GraphQueryService } from './../../../../services/graphql/graph-query.service';
import { ToastrService } from 'ngx-toastr';

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
  listPost: any;
  collections: any;
  jobs: any;
  info: any;
  constructor(private fb: FormBuilder,
              private data: DataService,
              private api: ApiService,
              private router: Router,
              private graphql: GraphQueryService,
              private toastr: ToastrService
              ) {
     this.data.currentUser.subscribe(user => {this.info = user ; });
     this.getAllJob();
     this.avatar = this.info ? this.info.photoUrl : '../../../../../assets/images/login.png';
     this.username = this.info ? this.info.name : null;
     this.provider = this.info ? this.info.provider : null;
     this.email = this.info ? this.info.email : null;
     this.startDate = new Date(1997, 10, 19);
     this.userInfo = this.fb.group({
      username: [this.info ? this.info.name : null , Validators.required],
      dob: [ this.startDate, Validators.required],
      email: [this.info ? this.info.email : null, Validators.required],
      gender: [this.info ? this.info.gender : true],
      job: this.info ? this.info.job.name : null,
      phone: [this.info ? this.info.phone : null]
     });
  }
  ngOnInit() {
    this.data.currentFavList.subscribe(favList => {
      this.collections = favList;
    });
    this.api.getAllRealEstate().subscribe(reList => {
      const info = JSON.parse(localStorage.getItem('loginInfo'));
      console.log(info);
      if (info) {
        this.listPost = [];
      } else {
        this.listPost = [];
      }
    });
  }

  changeAvatar() {
  const element: HTMLElement = document.querySelector('input[type="file"]') as HTMLElement;
  // element.click();
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
    const user = JSON.parse(localStorage.getItem('loginInfo'));
    const job = this.userInfo.get('job').value;
    const phone = this.userInfo.get('phone').value;
    const gender = this.userInfo.get('gender').value;
    this.updateUser(user.id, job, phone , gender).subscribe( res => {
      console.log(res);
      this.toastr.success('Cập nhật thông tin thành công', 'Cập nhật người dùng');
    }, error => {
      console.error(error);
    });
  }
  updateUser(userId, job, phone , gender) {
    return this.graphql.updateUser( userId, job, phone, gender);
  }

  onSelect(slug: any) {
    this.router.navigate(['chi-tiet', slug]);
  }

  getAllJob() {
    this.api.getAllJob().subscribe(
      jobs => this.jobs = jobs
    );
  }

}
