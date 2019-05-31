import { ApiService } from './../../../services/api/api.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { User } from 'src/app/model/user/user';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {
  userId: string;
  user: User;
  constructor(private route: ActivatedRoute, private api: ApiService, private router: Router) {
  }

  ngOnInit() {
    // this.userId = this.route.snapshot.paramMap.get('id');
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.userId = params.get('id');
    });
    this.getUserById( parseInt(this.userId, 10) );
  }
  getUserById(id: number) {
    this.api.getUserById(id).subscribe(userItem => {
      this.user = userItem;
    });
  }
  goPrevious() {
    const previousId = parseInt(this.userId, 10) - 1;
    this.router.navigate(['nguoi-dung', previousId]);
    this.getUserById(previousId);
  }
  goNext() {
    const nextId = parseInt(this.userId, 10) + 1;
    this.router.navigate(['nguoi-dung' , nextId]);
    this.getUserById(nextId);
  }
  goBack() {
    const selectedId = this.userId ? this.userId : null;
    this.router.navigate(['nguoi-dung', {id: selectedId}]);
  }
}
