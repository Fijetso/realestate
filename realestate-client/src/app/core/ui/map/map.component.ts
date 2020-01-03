import { ApiService } from 'src/app/services/api/api.service';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../../../environments/environment';
import { MarkerService } from './../../../services/map/marker.service';
import { Component, AfterViewInit, Input, OnInit } from '@angular/core';
import * as L from 'leaflet';
import 'leaflet-draw';
import { FormBuilder } from '@angular/forms';
// import * as LD from 'leaflet-draw'
import { ActivatedRoute } from '@angular/router';

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

// const markerHtmlStyles = `
// background-color: #FD784F;
// width: 3rem;
// height: 3rem;
// display: block;
// position: relative;
// border-radius: 3rem 3rem 0;
// transform: rotate(45deg);
// text-align: center;
// border: 1px solid #FFFFFF`;
// const customIcon = L.divIcon({
//   className: "my-custom-pin",
//   iconAnchor: [0, 24],
//   popupAnchor: [0, -36],
//   html: `<div style="${markerHtmlStyles}">12</div>`
// })
L.Marker.prototype.options.icon = iconDefault;
const purpose = [
  {
    id: 1,
    name: 'Mua'
  },
  {
    id: 2,
    name: 'Thuê'
  },
];

const reKinds = [
  {
    id: 1,
    name: 'Chung cư/Căn hộ'
  },
  {
    id: 2,
    name: 'Nhà riêng'
  },
];
@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements AfterViewInit, OnInit {
  protected map;
  protected geoData;
  protected curLat;
  protected curLong;
  protected geoJsonData;
  protected layer;
  protected markerData;
  @Input()
  protected reList;
  protected createPost: any;
  public isFullMap;
  quan: string;
  tinh: string;
  districtInfo: any;
  searchDetail: any;
  purpose: { id: number; name: string; }[];
  reKinds: { id: number; name: string; }[];
  districtList: any;
  wardList: import("d:/KLTN/realestate/realestate-client/src/app/model/address/address").Address;
  constructor(private markerService: MarkerService,
              private http: HttpClient,
              private api: ApiService,
              private fb: FormBuilder,
              private route: ActivatedRoute) {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(pos => {
        // console.info(pos.coords);
        this.curLat = pos.coords.latitude;
        this.curLong = pos.coords.longitude;
      });
    }
    this.purpose = purpose;
    this.reKinds = reKinds;
    this.quan = this.route.snapshot.queryParamMap.get('quan');
    this.tinh = this.route.snapshot.queryParamMap.get('tinh');
    this.api.getTradeFromDistrict(this.quan).subscribe(res => {
      this.reList = res;
      // console.info(res);
    });
    this.getDistrictName(this.tinh , this.quan);
    this.getAllDistrict(this.tinh);
    this.getAllWardFromDistrict(this.tinh , this.quan);
    this.searchDetail = this.fb.group({
      purpose: 1,
      query: '',
      reKind: 1,
      district: +this.quan,
      ward: ''
    });
  }
  ngOnInit(): void {
    this.isFullMap = false;
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
      center: this.curLat ? [this.curLat, this.curLong] : [10.823099, 106.629662],
      zoom: 12,
      drawControl: false,
      layers: [mapTile, streetTile]
    });
    const baseMaps = {
      'Default': mapTile,
      'Streets': streetTile,
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
    return this.http.get('assets/map/vietnam-district.geojson').subscribe(geoData => {
      this.geoData = geoData;
      const feature = this.geoData.features.map(feature => feature);
      // console.info(feature);
      L.geoJSON(this.geoData, {
        style: this.setStyle(),
        onEachFeature: (feature, featureLayer) => {
          featureLayer.bindTooltip(feature.properties.Ten);
          featureLayer.on({
            mouseover: this.highLightStyle,
            mouseout: this.normalStyle,
            click: () => { 
              console.info(feature);
            }
          });
        }
      }).addTo(this.map);
      const myCustomColour = 'turquoise';
      // console.info(pointArrays);
      const markerHtmlStyles = `
      background-color: ${myCustomColour};
      width: 3rem;
      height: 3rem;
      display: block;
      left: -1rem;
      top: -1rem;
      position: relative;
      border-radius: 3rem 3rem 0;
      transform: rotate(45deg);
      text-align: center;
      border: 1px solid #FFFFFF`;
      const markerHtmlStyles2 = `
      background-color: ${myCustomColour};
      width: 3rem;
      height: 3rem;
      display: block;
      position: relative;
      border-radius: 50%;
      transform: rotate(45deg);
      text-align: center;
      border: 1px solid #FFFFFF`;
      // for (let i = 0; i < feature.length; i++) {
      //   if (feature[i].properties.MaTP === '79') {
      //     const districtName = feature[i].properties.Ten;
      //     const cap = feature[i].properties.Cap + ' ';
      //     if (feature[i].properties.Ten === 'Quận 5') {
      //       const quan5Geo = console.info(i, feature[i].geometry.coordinates[0][0]);
      //       const realquan5Geo = feature[i].geometry.coordinates[0][0];
      //     }
      //     const coordinate = feature[i].geometry.coordinates[0].map(array => array);
      //     // first array coordinate is reverted
      //     const realCoordinate = Array.prototype.reverse.apply(coordinate[0][0]);
      //     // console.info(realCoordinate);
      //     const marker = L.marker(realCoordinate, {
      //       icon: L.divIcon({
      //         className: 'my-custom-pin',
      //         iconAnchor: [0, 24],
      //         popupAnchor: [0, -36],
      //         html: `<div style="${markerHtmlStyles}">${+districtName.replace(cap, '') > 0
      //         ? 'Q. ' + districtName.replace(cap, '') : 
      //         districtName.replace(cap, '')}</div>`
      //       })
      //     }).bindTooltip(districtName).bindPopup(districtName).addTo(this.map);
      //   }
      //   // console.info(`Added marker ${i+1}`);
      // }
    }, error => console.error(error));
  }
  highLightStyle($event) {
    const layer = $event.target;
    layer.setStyle(
      {
        fillColor: 'blue',
        color: 'orange',
        fillOpacity: 0.5,
        weight: 0.8,
        stroke: true
      }
    );
    if (!L.Browser.ie && !L.Browser.opera) {
      layer.bringToFront();
    }
  }

  normalStyle($event) {
    const layer = $event.target;
    layer.setStyle(
      {
        fillColor: 'white',
        color: 'blue',
        fillOpacity: 0.2,
        weight: 0.2
      }
    );
    if (!L.Browser.ie && !L.Browser.opera) {
      layer.bringToFront();
    }
  }

  setStyle(): L.PathOptions | L.StyleFunction<any> {
    return {
      fillColor: 'white',
      color: 'blue',
      fillOpacity: 0.2,
      weight: 0.2
    };
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


  mapFullScreen(event) {
    this.isFullMap = !this.isFullMap;
  }

  getDistrictName(provinceId, districtId) {
    this.api.getDistrictNameById(provinceId, districtId).subscribe(district => {
      this.districtInfo = district;
      // console.info(this.districtInfo);
    });
  }

  getAllDistrict(provinceId){
    this.api.getDistrictFromProvinceId(provinceId).subscribe(districtList => {
      this.districtList = districtList;
    });
  }

  getAllWardFromDistrict(provinceId, districtId) {
    this.api.getWardFromDistrictId(provinceId, districtId).subscribe(wardList => {
      console.info(wardList);
      this.wardList = wardList;
    });
  }

  onChangeDistrict() {
    const district = this.searchDetail.get('district').value;
    this.getAllWardFromDistrict(this.tinh, district);
  }
}
