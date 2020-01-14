import { ApiService } from './../../services/api/api.service';
import { Component, OnInit, Input } from '@angular/core';
import { DataService } from '../../services/data/data.service';
@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.scss']
})
export class FavoriteComponent implements OnInit {

  constructor(private api: ApiService, private data: DataService) {}
  favCatTitle = 'Bạn đã thích';
  viewmoreText = 'Xem thêm';
  iconName = 'fas fa-angle-right';
  @Input()
  favList: any;
  ngOnInit() {
  }
}
