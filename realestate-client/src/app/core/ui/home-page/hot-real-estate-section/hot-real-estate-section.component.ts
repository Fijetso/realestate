import { ApiService } from './../../../../services/api/api.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-hot-real-estate-section',
  templateUrl: './hot-real-estate-section.component.html',
  styleUrls: ['./hot-real-estate-section.component.scss']
})
export class HotRealEstateSectionComponent implements OnInit {
  dataHotRE: any = null;
  isHotRE = true;
  @Input()
  title: any;
  constructor(private api: ApiService) { }
  ngOnInit() {
    this.getHotRE();
  }

  getHotRE() {
    this.api.getHotRealEstate().subscribe(hotREList => {
      this.dataHotRE = hotREList;
    });
  }
}
