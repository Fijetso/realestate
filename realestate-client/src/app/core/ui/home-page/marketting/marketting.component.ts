import { ApiService } from './../../../../services/api/api.service';
import { Component, OnInit, ViewChild, Output, EventEmitter  } from '@angular/core';
import { FormControl, FormBuilder } from '@angular/forms';
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
  reKindValue: string;
  stateNameKey: string;
  @Output()
  receiveREKind: EventEmitter <string> = new EventEmitter <string>();
  constructor(private api: ApiService, private fb: FormBuilder) {

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
}
