import { CommonService } from './../../../../services/common/common.service';
import { GetIdFromNamePipe } from './../../../../ultility/pipe/get-id-from-name.pipe';
import { ApiService } from './../../../../services/api/api.service';
import { Component, OnInit, ViewChild, Output,EventEmitter  } from '@angular/core';
import { FormControl  } from '@angular/forms';
import { NavigationExtras, Router } from '@angular/router';
export interface State {
  name: string;
}
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
  tradeFromDistrict: any;
  constructor(private api: ApiService, private getIdFromName: GetIdFromNamePipe, private common : CommonService,private router: Router) {

  }

  myControl = new FormControl();
  states: any;

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
      this.states = districtList;
    });
  }
  onSubmitSearch( value) {
    this.stateId = this.getIdFromName.transform(this.states, value);
    this.api.getTradeFromDistrict(this.stateId).subscribe(tradeList => {
      this.tradeFromDistrict = tradeList;
      this.api.setData(this.tradeFromDistrict);
      // this.toastr.warning('Chức năng chưa hoàn thiện');
      const navExtras: NavigationExtras = {
        queryParams: {
          quan: this.common.changeToSlug(value),
          gia: this.common.changeToSlug('Giá thấp nhất'),
          loai: this.common.changeToSlug('Nhà ở chung cư')
        },
        state: { data:  tradeList, title: value}
      };
      this.router.navigate(['tim-kiem'], navExtras);
    });

  }
}
