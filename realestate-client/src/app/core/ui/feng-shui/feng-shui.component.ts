import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ApiService } from './../../../services/api/api.service';
import * as moment from 'moment';
@Component({
  selector: 'app-feng-shui',
  templateUrl: './feng-shui.component.html',
  styleUrls: ['./feng-shui.component.scss']
})
export class FengShuiComponent implements OnInit {
  fengShui: any;
  fengShuiTradeList: any;
  isLoading= true;
  constructor(private fb: FormBuilder, private api: ApiService) {
    this.fengShui = this.fb.group({
      dob: new Date(1989, 5, 1),
      gender: true
    });
  }

  ngOnInit() {
    const dob = moment(new Date(this.fengShui.get('dob').value).toLocaleDateString()).format('DD/MM/YYYY').toString();
    const isFemale = this.fengShui.get('gender').value;
    this.getRecommendFengshui(dob, isFemale);
  }
  getRecommendFengshui(dob: string, isFemale: string) {
    console.log( dob, isFemale);
    return this.api.getRecommendFengshui(dob, isFemale).subscribe(
      res => {
        this.fengShuiTradeList = res;
        this.isLoading = false;
      }
    );
  }

  submitFengShui() {
    this.isLoading = true;
    const dob = moment(new Date(this.fengShui.get('dob').value).toLocaleDateString()).format('DD/MM/YYYY').toString();
    const isFemale = this.fengShui.get('gender').value;
    this.getRecommendFengshui(dob, isFemale);
  }
  onSelectChange() {
    this.isLoading = true;
    const dob = moment(new Date(this.fengShui.get('dob').value).toLocaleDateString()).format('DD/MM/YYYY').toString();
    const isFemale = this.fengShui.get('gender').value;
    this.getRecommendFengshui(dob, isFemale);
  }
  onChangeDate() {
    this.isLoading = true;
    const dob = moment(new Date(this.fengShui.get('dob').value).toLocaleDateString()).format('DD/MM/YYYY').toString();
    const isFemale = this.fengShui.get('gender').value;
    this.getRecommendFengshui(dob, isFemale);
  }
}
