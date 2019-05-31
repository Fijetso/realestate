import { ApiService } from './../../../../services/api/api.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-real-estate-appraised-section',
  templateUrl: './real-estate-appraised-section.component.html',
  styleUrls: ['./real-estate-appraised-section.component.scss']
})
export class RealEstateAppraisedSectionComponent implements OnInit {

  dataAppraised: any = null;
  appraised= true;
  constructor(private api: ApiService) { }
  ngOnInit() {
    this.getAllRealEstate();
  }

  getAllRealEstate() {
    this.api.getAllRealEstate().subscribe(trade => {
      this.dataAppraised = trade;
    });
  }
}
