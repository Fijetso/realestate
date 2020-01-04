import { element } from 'protractor';
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
    //  this.activatedRoute.params.subscribe((params)=> {
    //     this.reId = +params['id'];
    //     console.log(this.reId);
    //   })
    this.activatedRoute.queryParams.subscribe(params => {
      this.provinceId = params.tinh;
      this.reId = params.quan;
      console.log(this.provinceId, this.reId);
    });
  }
}
