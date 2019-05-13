import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.scss']
})
export class FavoriteComponent implements OnInit {

  constructor() {}

  catTitle = 'Bất động sản an toàn đã được thẩm định';
  viewmoreText = 'Xem thêm';
  iconName = 'fas fa-angle-right';

  ngOnInit() {
  }
}
