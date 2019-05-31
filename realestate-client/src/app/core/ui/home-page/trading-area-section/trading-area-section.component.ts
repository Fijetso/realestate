import { ApiService } from './../../../../services/api/api.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trading-area-section',
  templateUrl: './trading-area-section.component.html',
  styleUrls: ['./trading-area-section.component.scss']
})
export class TradingAreaSectionComponent implements OnInit {
  hotPlaceList: any = null;
  tradingArea = true;
  constructor(private api: ApiService) { }

  ngOnInit() {
    this.api.getDistrictFromProvinceId(79).subscribe(distList => {
      this.hotPlaceList = distList;
    });
  }

}