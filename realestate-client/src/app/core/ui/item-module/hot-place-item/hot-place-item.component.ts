import { Router } from '@angular/router';
import { Component, OnInit, Input, OnDestroy, Injectable } from '@angular/core';
import { ApiService } from 'src/app/services/api/api.service';
import { ToastrService } from 'ngx-toastr';
import { DataService } from './../../../../services/data/data.service';
import { nonAccentVietnamese } from './../../../../ultility/functions/remove-sign';

@Component({
  selector: 'app-hot-place-item',
  templateUrl: './hot-place-item.component.html',
  styleUrls: ['./hot-place-item.component.scss']
})
export class HotPlaceItemComponent implements OnInit, OnDestroy {
  @Input() place: any;
  src = null;
  tradeFromDistrict: any;
  reKindSelected: string;
  ngOnDestroy(): void {
    this.api.setData(this.tradeFromDistrict);
  }

  constructor(private api: ApiService, private router: Router, private toastr: ToastrService, private data: DataService) { }

  ngOnInit() {
    this.data.currentTradeKindSelected.subscribe(reKind => {
      this.reKindSelected = reKind;
    });
  }

  getTradeByDistrict(districtId: any) {
    this.router.navigate(['/tim-kiem/' + nonAccentVietnamese(this.reKindSelected)], {queryParams: {tinh: 79, quan: districtId}});
  }
  onSelect(districtId: any) {
    this.getTradeByDistrict(districtId);
    this.api.setData(this.tradeFromDistrict);
    this.router.navigate(['tim-kiem'], districtId);
  }
  updateUrl() {
    this.src = '../../../../../assets/images/default.png';
  }
}
