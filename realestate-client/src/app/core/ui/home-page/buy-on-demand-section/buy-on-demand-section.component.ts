import { Component, OnInit, Input } from '@angular/core';
import { ApiService } from 'src/app/services/api/api.service';

@Component({
  selector: 'app-buy-on-demand-section',
  templateUrl: './buy-on-demand-section.component.html',
  styleUrls: ['./buy-on-demand-section.component.scss']
})
export class BuyOnDemandSectionComponent implements OnInit {
  buyOnDemandData: any;
  demandOption: any;
  @Input()
  tradeKindSelected: any;
  constructor(private api: ApiService) {
    this.demandOption = 3;
  }

  ngOnInit() {
    this.getData();
  }
  getData() {
    this.api.getAllRealEstate().subscribe(data => {
     this.buyOnDemandData = data;
    });
  }


  openOndemandPopUp(){
    
  }
}
