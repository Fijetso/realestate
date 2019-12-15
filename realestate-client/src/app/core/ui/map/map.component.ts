import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../../../environments/environment';
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
  protected map;
  protected geoData
  protected curLat;
  protected curLong;
  protected geoJsonData;
  protected layer;
  private markerData;
  constructor(private markerService: MarkerService, private http: HttpClient) {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(pos => {
        // console.info(pos.coords);
        this.curLat = pos.coords.latitude;
        this.curLong = pos.coords.longitude;
        // this.browserLocation.lat = pos.coords.latitude;
        // this.browserLocation.lng = pos.coords.longitude;
        // console.info(this.curLat,this.curLong);
      })
    }
  }

  initMap() {
    const mapTile = L.tileLayer(
      // 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
      'https://1.base.maps.api.here.com' +
      '/maptile/2.1/maptile/newest/normal.day/{z}/{x}/{y}/256/png' +
      '?app_id=' + environment.heremap.appId + '&app_code=' + environment.heremap.appCode +
      '&lg=' + environment.heremap.defaultLang
      , {
        maxZoom: 19,
        // attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
        attribution: '&copy; <a href="http://developer.here.com">HERE</a>',
        updateWhenZooming: true,
      });
    this.layer = mapTile;
    const streetTile = L.tileLayer(
      // 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
      'https://1.base.maps.api.here.com' +
      '/maptile/2.1/streettile/newest/normal.day/{z}/{x}/{y}/256/png' +
      '?app_id=' + environment.heremap.appId + '&app_code=' + environment.heremap.appCode +
      '&lg=' + environment.heremap.defaultLang
      , {
        maxZoom: 19,
        // attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
        attribution: '&copy; <a href="http://developer.here.com">HERE</a>',
        updateWhenZooming: true,
      });

    const aLabelTile = L.tileLayer(
      // 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
      'https://1.base.maps.api.here.com' +
      '/maptile/2.1/alabeltile/newest/normal.day/{z}/{x}/{y}/256/png' +
      '?app_id=' + environment.heremap.appId + '&app_code=' + environment.heremap.appCode +
      '&lg=' + environment.heremap.defaultLang
      , {
        maxZoom: 19,
        // attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
        attribution: '&copy; <a href="http://developer.here.com">HERE</a>',
        updateWhenZooming: true,
      });
    this.map = L.map('map', {
      center: this.curLat?[this.curLat,this.curLong]:[10.823099, 106.629662],
      zoom: 8,
      drawControl: true,
      layers: [mapTile, streetTile]
    });
    const baseMaps = {
      "Default": mapTile,
      "Streets": streetTile,
    };
    L.control.layers(baseMaps).addTo(this.map);
    // mapTile.addTo(this.map);
  }

  ngAfterViewInit(): void {
    this.initMap();
    this.loadGeoJsonData();
    this.markerService.makeCapitalMarkers(this.map);
  }
  loadGeoJsonData() {
    this.getGeoJsonFile();
  }
  getGeoJsonFile(): any {
    return this.http.get('assets/map/vietnam-ward-map.geojson').subscribe(geoData => {
      this.geoData = geoData;
      const feature = this.geoData.features.map(feature => feature);
      // console.info(feature);
      const wardNameList = feature.map(feature =>feature.properties.Ten);
      // wardNameList.forEach(ward => {
      //   console.info(ward);
      // });
      const pointArrays = feature.map(feature => feature.geometry.coordinates[0].map(array => L.latLng(array[0])));
      // pointArrays.map(point =>console.info(point));
      L.geoJSON(this.geoData,{style : this.setStyle()}).on('mouseover',(e)=>this.setStyleMouseOverWard(e)).addTo(this.map);
      const myCustomColour = 'turquoise'

      const markerHtmlStyles = `
      background-color: ${myCustomColour};
      width: 2rem;
      height: 2rem;
      display: block;
      left: -1rem;
      top: -1rem;
      position: relative;
      border-radius: 2rem 2rem 0;
      transform: rotate(45deg);
      border: 1px solid #FFFFFF`
      const icon = L.divIcon({
        className: "my-custom-pin",
        iconAnchor: [0, 24],
        popupAnchor: [0, -36],
        html: `<span style="${markerHtmlStyles}" />`
      })
      // L.marker([this.curLat, this.curLong],{icon:icon}).bindPopup(`[${this.curLat},${this.curLong}]`).addTo(this.map);
      // L.circleMarker([this.curLat, this.curLong], { radius: 5 }).bindPopup(`[${this.curLat},${this.curLong}]`).addTo(this.map);
      // pointArrays.map(point => console.info(point[0]));
      // pointArrays.forEach(point => {
      //   // console.info(JSON.stringify(point[0]));
      //   return L.marker([this.curLat, this.curLong]).bindPopup(`[${point[0].lat},${point[0].lng}]`).addTo(this.map);
      // });
      for(let i = 0; i<1000;i++){
        const wardName = feature[i].properties.Ten;
        const coordinate = feature[i].geometry.coordinates[0].map(array => array[0]);
        //coordinate is reverse in map coordinate
        const realCoordinate=Array.prototype.reverse.apply(coordinate[0]);
        // console.info(realCoordinate);
        const marker = L.marker(realCoordinate,{icon:icon}).bindTooltip(wardName).bindPopup(wardName).addTo(this.map);
        console.info(`Added marker ${i+1}`);
      }
    }, error => console.error(error));
  }
  setStyleMouseOverWard(e) {
    return {
      fillColor: 'red',
      color: 'blue',
      fillOpacity: 0.8,
      weight: 0.3
    }
  }
  pointTolayer(point): any {
    return L.marker(point).addTo(this.map);
  }
  setStyle(): L.PathOptions | L.StyleFunction<any> {
    return {
      fillColor: 'red',
      color: 'blue',
      fillOpacity: 0.2,
      weight: 0.3
    }
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
  onEachFeature(feature, layer): any {
    if (feature.properties) {
      layer.bindPopup(feature.properties.QuanHuyen)
    }
  }
}
