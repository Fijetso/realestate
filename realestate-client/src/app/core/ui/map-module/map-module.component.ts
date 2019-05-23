import { Component, OnInit } from '@angular/core';
import { Map } from 'src/app/model/map/map';
@Component({
  selector: 'app-map-module',
  templateUrl: './map-module.component.html',
  styleUrls: ['./map-module.component.scss']
})
export class MapModuleComponent implements OnInit {
  constructor() { }
  public origin: any;
  public destination: any;
  public map: Map = {
    lat: 10.8698354,
    lng : 106.8027391,
    zoom : 17
  };

  ngOnInit() {
    this.getDirection();
  }
  getDirection() {
    this.origin = this.map;
    this.destination = {lat: 10.8830067, lng: 106.7795138};

    // this.origin = 'Taipei Main Station';
    // this.destination = 'Taiwan Presidential Office';
    console.log(this.origin, this.destination);
  }
}
