import { ToastrService } from 'ngx-toastr';
import { FilterPipe } from '../../ultility/pipe/filter.pipe';
import { CommonService } from './../../services/common/common.service';
import { ApiService } from './../../services/api/api.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user/user';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  users: User[] = [];
  user: User;
  userKinds = [
    {
      id: 1,
      name: 'Chủ nhà'
    },
    {
      id: 2,
      name: 'Môi giới'
    }
  ];
  searchTerm: string;
  constructor(
    private api: ApiService,
    private common: CommonService,
    private router: Router,
    private route: ActivatedRoute,
    private toastr: ToastrService
  ) {
    this.user = {
      id: null,
      name: 'Danh Thanh',
      email: '',
      phone: '',
      password: '',
      gender: false,
      birthdate: new Date('11/19/1995'),
      userKind: 1
    };
    this.searchTerm = '';
  }
  ngOnInit() {
    // this.getAllUser();
    // this.common.userObservable.subscribe(res => {
    //   this.getAllUser();
    //   }
    // );
  }
  getAllUser() {
    this.api.getAllUser().subscribe(listUser => {
      this.users = listUser;
      console.log(this.users);
    });
  }
  getUserById(id: number) {
    this.api.getUserById(id).subscribe(userItem => {
      this.user = userItem;
      console.log(this.user);
    });
  }
  createUser(user: User) {
    console.log(user);
    this.api.createUser(user).subscribe();
  }
  deleteUser(id: number) {
    this.api.deleteUser(id).subscribe(
      success => {
        alert('Delete user successfully');
        this.common.notifyUserDataChanged();
      },
      error => alert('Delete user failed')
    );
  }
  goToUserDetail(userId: string) {
    this.router.navigate(['nguoi-dung', userId]);
  }
}
