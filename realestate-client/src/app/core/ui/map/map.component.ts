import { environment } from './../../../../environments/environment.prod';
import { MarkerService } from './../../../services/map/marker.service';
import { Component, AfterViewInit } from '@angular/core';
import * as L from 'leaflet';
import 'leaflet-draw'
// import * as LD from 'leaflet-draw'

const iconRetinaUrl = 'assets/map/marker-icon-2x.png';
const iconUrl = 'assets/map/marker-icon.png';
const shadowUrl = 'assets/map/marker-shadow.png';
const iconDefault = L.icon({
  iconRetinaUrl,
  iconUrl,
  shadowUrl,
  iconSize: [25, 41],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
  tooltipAnchor: [16, -28],
  shadowSize: [41, 41]
});
L.Marker.prototype.options.icon = iconDefault;

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements AfterViewInit {

  private map;
  constructor(private markerService: MarkerService) { }

  initMap() {
    this.map = L.map('map', {
      center: [10.823099, 106.629662],
      zoom: 8,
      drawControl: true
    });
    const tile = L.tileLayer(
      // 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
      'https://1.base.maps.api.here.com' +
      '/maptile/2.1/maptile/newest/normal.day/{z}/{x}/{y}/256/png' +
      '?app_id=' + environment.heremap.appId + '&app_code=' + environment.heremap.appCode
      , {
        maxZoom: 19,
        // attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
        attribution: '&copy; <a href="http://developer.here.com">HERE</a>',
        updateWhenZooming: true,
      })
    tile.addTo(this.map);
    console.log('Map view inited');
  }

  ngAfterViewInit(): void {
    this.initMap();
    // this.addControls(this.map);
    this.markerService.makeCapitalMarkers(this.map);
  }
  addControls(map: L.Map) {
    const drawnItems = L.featureGroup();
    map.addLayer(drawnItems);
    const drawControl = new L.Control.Draw({
      edit: {
        featureGroup: drawnItems
      }
    });
    map.addControl(drawControl);
  }
}
