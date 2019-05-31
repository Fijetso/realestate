import { ApiService } from './../../../../services/api/api.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl  } from '@angular/forms';
import { Observable, Subject, merge } from 'rxjs';
import {
  startWith,
  map,
  debounceTime,
  distinctUntilChanged,
  filter
} from 'rxjs/operators';

export interface State {
  name: string;
}
@Component({
  selector: 'app-marketting',
  templateUrl: './marketting.component.html',
  styleUrls: ['./marketting.component.scss']
})
export class MarkettingComponent implements OnInit {
  selectedVal: string;
  stateNameKey: string;
  constructor(private api: ApiService) {

  }

  myControl = new FormControl();
  states: any;

  ngOnInit() {
    this.selectedVal = 'mua';
    this.getDistrictList();
  }

  public onValChange(val: string) {
    this.selectedVal = val;
  }
  getDistrictList() {
    this.api.getDistrictFromProvinceId(79).subscribe(districtList => {
      this.states = districtList;
    });
  }
}
