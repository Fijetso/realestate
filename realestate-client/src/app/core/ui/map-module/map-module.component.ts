import {
  Component,OnInit
} from '@angular/core';
@Component({
  selector: 'app-map-module',

  templateUrl: './map-module.component.html',

  styleUrls: ['./map-module.component.scss'],
})
export class MapModuleComponent implements OnInit{
  public markers: {lat: number, long: number}[];   // Map markers (relevance depends on map center)

  constructor()
  {
    // some map markers
    this.markers = [
      { lat: 10.823099, long: 106.629662   },
      { lat: 10.5, long: 106.5 },
      { lat: 10.3  , long: 106.7 },
      { lat: 10.7, long: 106.8 },
    ];
  }
  ngOnInit(): void {
  }
}