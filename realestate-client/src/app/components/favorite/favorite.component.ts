import { ApiService } from './../../services/api/api.service';
import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.scss']
})
export class FavoriteComponent implements OnInit {

  constructor(private api: ApiService) {}
  favCatTitle = 'Yêu thích';
  viewmoreText = 'Xem thêm';
  iconName = 'fas fa-angle-right';
  dataFavorite = null;
  isFavRE = true;
  ngOnInit() {
    this.getFavTrade();
  }
  getFavTrade() {
    this.api.getFavRealEstate().subscribe(favList => {
        this.dataFavorite = favList;
    });
  }
}