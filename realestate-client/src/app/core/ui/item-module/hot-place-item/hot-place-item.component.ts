import { CommonService } from './../../../../services/common/common.service';
import { Router, NavigationExtras } from '@angular/router';
import { Component, OnInit, Input, OnDestroy, Injectable } from '@angular/core';
import { ApiService } from 'src/app/services/api/api.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-hot-place-item',
  templateUrl: './hot-place-item.component.html',
  styleUrls: ['./hot-place-item.component.scss']
})
export class HotPlaceItemComponent implements OnInit, OnDestroy {
  @Input() place: any;
  src = null;
  tradeFromDistrict: any;
  ngOnDestroy(): void {
    this.api.setData(this.tradeFromDistrict);
  }

  constructor(
    private api: ApiService,
    private router: Router,
    private toastr: ToastrService,
    private common: CommonService
  ) {}

  ngOnInit() {}

  getTradeByDistrict(districtId: any) {
    this.router.navigate(['/tim-kiem'], {queryParams: {tinh: 79, quan: districtId}});
  }
  onSelect(place: any) {
    this.api.getTradeFromDistrict(place.id).subscribe(tradeList => {
      this.tradeFromDistrict = tradeList;
      this.api.setData(this.tradeFromDistrict);
      // this.toastr.warning('Chức năng chưa hoàn thiện');
      const navExtras: NavigationExtras = {
        queryParams: {
          quan: this.common.changeToSlug(place.nameWithType),
          gia: this.common.changeToSlug('Giá thấp nhất'),
          loai: this.common.changeToSlug('Nhà ở chung cư')
        },
        state: { data:  tradeList, title: place.nameWithType}
      };
      this.router.navigate(['tim-kiem'], navExtras);
    });

    // this.router.navigate(['tim-kiem'], {state: { data:  JSON.stringify(this.tradeFromDistrict)}});
  }
  updateUrl() {
    this.src = '../../../../../assets/images/default.png';
  }
}
