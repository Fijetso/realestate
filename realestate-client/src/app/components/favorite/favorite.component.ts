import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.scss']
})
export class FavoriteComponent implements OnInit {

  constructor() {}
  favCatTitle = 'Yêu thích';
  viewmoreText = 'Xem thêm';
  iconName = 'fas fa-angle-right';

  ngOnInit() {
  }
}
