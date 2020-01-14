import { CommonService } from './../../../../services/common/common.service';
import { GetIdFromNamePipe } from './../../../../ultility/pipe/get-id-from-name.pipe';
import { ApiService } from './../../../../services/api/api.service';
import { Component, OnInit, Output, EventEmitter  } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { nonAccentVietnamese } from './../../../../ultility/functions/remove-sign';
import { DataService } from './../../../../services/data/data.service';
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
  tradeKindSelected: any;
  constructor(private api: ApiService, private fb: FormBuilder, private router: Router, private data: DataService) {
    this.searchForm = this.fb.group({
      district: null
    });
    this.tradeKindSelected  = 1;
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
    this.router.navigate(['tim-kiem'], {queryParams:
    {
      tinh: 79,
      quan: districtId,
      maloaigd: this.tradeKindSelected
    }});
  }
  onChangeDistrict() {
    const district = this.searchForm.get('district').value;
    console.log(district);
    this.getTradeByDistrict(district);
  }
  changeTradeKind(val) {
    this.data.changeTradeKindSelected(val);
  }
}
