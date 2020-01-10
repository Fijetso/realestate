import { CommonService } from './../../../../services/common/common.service';
import { GetIdFromNamePipe } from './../../../../ultility/pipe/get-id-from-name.pipe';
import { ApiService } from './../../../../services/api/api.service';
import { Component, OnInit, Output, EventEmitter  } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-marketting',
  templateUrl: './marketting.component.html',
  styleUrls: ['./marketting.component.scss']
})
export class MarkettingComponent implements OnInit {
  reKindValue: string;
  stateNameKey: string;
  stateId: number;
  @Output()
  receiveREKind: EventEmitter <string> = new EventEmitter <string>();
  searchForm: any;
  districtList: any;
  constructor(private api: ApiService, private fb: FormBuilder, private router: Router) {
    this.searchForm = this.fb.group({
      district: 760
    });
  }
  ngOnInit() {
    this.reKindValue = 'mua';
    this.getDistrictList();
    this.receiveREKind.emit(this.reKindValue);
  }

  public onValChange(val: string) {
    this.reKindValue = val;
    this.receiveREKind.emit(this.reKindValue);
  }
  getDistrictList() {
    this.api.getDistrictFromProvinceId(79).subscribe(districtList => {
      this.districtList = districtList;
    });
  }

  getTradeByDistrict(districtId: any) {
    this.router.navigate(['/tim-kiem'], {queryParams: {tinh: 79, quan: districtId}});
  }
  onChangeDistrict() {
    const district = this.searchForm.get('district').value;
    console.log(district);
    this.getTradeByDistrict(district);
  }
}
