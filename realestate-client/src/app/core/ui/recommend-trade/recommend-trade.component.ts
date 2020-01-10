import { Component, OnInit } from '@angular/core';
import { ApiService } from './../../../services/api/api.service';

@Component({
  selector: 'app-recommend-trade',
  templateUrl: './recommend-trade.component.html',
  styleUrls: ['./recommend-trade.component.scss']
})
export class RecommendTradeComponent implements OnInit {
  tradeRecommendList: any;

  constructor(private api: ApiService) {
    this.getTradeRecommend('Kinh doanh', [760, 774], [50, 75, 50, 100, 50, 50], [1, 2, 3, 3, 3]).subscribe(res => {
      this.tradeRecommendList = res;
      console.log(this.tradeRecommendList);
    });
   }

  ngOnInit() {
  }

  getTradeRecommend(job, districtIdList, squareList, priceList) {
    return this.api.getTradeRecommend(job, districtIdList, squareList, priceList);
  }
}
