import { ApiService } from './../../services/api/api.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user/user';
import { UserKind } from 'src/app/model/user-kind/user-kind';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  private users: User[] = [];
  private user: User ;

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
  constructor(private api: ApiService) { }
  ngOnInit() {
    this.getAllUser();
    this.getUserById(1);
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
    this.api.createUser(user).subscribe(
      success => alert('Create successfully'),
      error => alert('Create user failed')
    );
  }

}
