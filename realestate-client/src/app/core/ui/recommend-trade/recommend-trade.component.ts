import { Component, OnInit, Input } from '@angular/core';
import { ApiService } from './../../../services/api/api.service';
import { DataService } from './../../../services/data/data.service';

@Component({
  selector: 'app-recommend-trade',
  templateUrl: './recommend-trade.component.html',
  styleUrls: ['./recommend-trade.component.scss']
})
export class RecommendTradeComponent implements OnInit {
  tradeRecommendList: any;
  user: any;
  @Input()
  tradeKindSelected: any;
  constructor(private api: ApiService, private data: DataService) {
    this.data.currentUser.subscribe(user => {
      this.user = user;
      if (this.user) {
        this.getTradeRecommendByUser(this.user.id);
      }
    });
   }

  ngOnInit() {
  }

  getTradeRecommendAnonymous(job, districtIdList, squareList, priceList) {
    return this.api.getTradeRecommend(job, districtIdList, squareList, priceList);
  }

  getTradeRecommendByUser(userId) {
    return this.api.getTradeRecommendByUser(userId).subscribe(res => {
      this.tradeRecommendList = res;
      console.log(this.tradeRecommendList);
    });
  }
}
