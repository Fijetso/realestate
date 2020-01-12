import { ApiService } from './../../../../services/api/api.service';
import { Component, OnInit, Output, EventEmitter  } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { nonAccentVietnamese } from './../../../../ultility/functions/remove-sign';
@Component({
  selector: 'app-marketting',
  templateUrl: './marketting.component.html',
  styleUrls: ['./marketting.component.scss']
})
export class MarkettingComponent implements OnInit {

  stateNameKey: string;
  searchForm: any;
  districtList: any;
  tradeKinds: any;
  tradeKindSelected: string;
  constructor(private api: ApiService, private fb: FormBuilder, private router: Router) {
    this.searchForm = this.fb.group({
      district: null
    });
    this.tradeKindSelected  = 'Mua';
  }
  ngOnInit() {
    this.getDistrictList();
    this.getTradeKinds();
  }
  getTradeKinds() {
    this.api.getTradeKind().subscribe(tradeKinds => {
      this.tradeKinds = tradeKinds;
    });
  }

  getDistrictList() {
    this.api.getDistrictFromProvinceId(79).subscribe(districtList => {
      this.districtList = districtList;
    });
  }

  getTradeByDistrict(districtId: any) {
    this.router.navigate([nonAccentVietnamese(this.tradeKindSelected)], {queryParams: {tinh: 79, quan: districtId}});
  }
  onChangeDistrict() {
    const district = this.searchForm.get('district').value;
    console.log(district);
    this.getTradeByDistrict(district);
  }
}
