import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-why-choose-section',
  templateUrl: './why-choose-section.component.html',
  styleUrls: ['./why-choose-section.component.scss']
})
export class WhyChooseSectionComponent implements OnInit {
  constructor() { }

  selectedVal: string;
  whyChooseTitle = 'Tại sao bạn chọn RealEstate?';
  ngOnInit() {
    this.selectedVal = 'buyer';
  }

  public onValChange(val: string) {
    this.selectedVal = val;
  }
}
