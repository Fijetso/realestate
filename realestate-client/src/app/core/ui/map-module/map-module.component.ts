import { ActivatedRoute } from '@angular/router';
import {
  Component, OnInit
} from '@angular/core';
@Component({
  selector: 'app-map-module',

  templateUrl: './map-module.component.html',

  styleUrls: ['./map-module.component.scss'],
})
export class MapModuleComponent implements OnInit {
  reId: number;
  provinceId: number;
  constructor(private activatedRoute: ActivatedRoute) {

  }
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.provinceId = params.tinh;
      this.reId = params.quan;
      console.log(this.provinceId, this.reId);
    });
  }
}
