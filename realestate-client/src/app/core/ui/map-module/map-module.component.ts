import {
  Component, OnInit
} from '@angular/core';
@Component({
  selector: 'app-map-module',

  templateUrl: './map-module.component.html',

  styleUrls: ['./map-module.component.scss'],
})
export class MapModuleComponent implements OnInit {
  public markers: { lat: number, long: number }[];   // Map markers (relevance depends on map center)

  constructor() {
  }
  ngOnInit(): void {
  }
}