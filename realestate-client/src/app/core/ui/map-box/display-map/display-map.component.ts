import { ApiService } from 'src/app/services/api/api.service';
import { HttpClient } from '@angular/common/http';
import { Component, AfterViewInit, Input, OnInit } from '@angular/core';
import * as L from 'leaflet';
import 'leaflet-draw';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MarkerService } from '../../../../services/map/marker.service';
import { Map } from 'mapbox-gl';

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
  selector: 'app-display-map',
  templateUrl: './display-map.component.html',
  styleUrls: ['./display-map.component.scss']
})
export class DisplayMapComponent implements AfterViewInit, OnInit {
  protected mapGL: Map;
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
  tradeKinds: any;
  reKinds: any;
  districtList: any;
  wardList: any;
  query: any;
  newREKind: any;
  layerId = 'streets';
  style: string;
  constructor(private markerService: MarkerService,
              private http: HttpClient,
              private api: ApiService,
              private fb: FormBuilder,
              private route: ActivatedRoute) {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(pos => {
        // console.log(pos.coords);
        this.curLat = pos.coords.latitude;
        this.curLong = pos.coords.longitude;
      });
    }
    this.getTradeKind();
    this.getREKind();
    this.quan = this.route.snapshot.queryParamMap.get('quan');
    this.tinh = this.route.snapshot.queryParamMap.get('tinh');
    this.api.getTradeFromDistrict(this.quan).subscribe(res => {
      this.reList = res;
      console.log(res);
    });
    this.getDistrictName(this.tinh , this.quan);
    this.getAllDistrict(this.tinh);
    this.getAllWardFromDistrict(this.tinh , this.quan);
    this.newREKind = 1;
    this.searchDetail = this.fb.group({
      tradeKind: 1,
      query: '',
      reKind: 1,
      district: +this.quan,
      ward: ''
    });
  }
  ngOnInit(): void {
    this.isFullMap = false;
    this.changeStyle(this.layerId);
  }

  changeStyle(layerId: string) {
    this.style = `mapbox://styles/mapbox/${layerId}-v9`;
  }

  ngAfterViewInit(): void {
  }

  mapFullScreen(event) {
    this.isFullMap = !this.isFullMap;
  }

  getDistrictName(provinceId, districtId) {
    this.api.getDistrictNameById(provinceId, districtId).subscribe(district => {
      this.districtInfo = district;
      // console.log(this.districtInfo);
    });
  }

  getAllDistrict(provinceId) {
    this.api.getDistrictFromProvinceId(provinceId).subscribe(districtList => {
      this.districtList = districtList;
    });
  }

  getAllWardFromDistrict(provinceId, districtId) {
    this.api.getWardFromDistrictId(provinceId, districtId).subscribe(wardList => {
      // console.log(wardList);
      this.wardList = wardList;
    });
  }

  onChangeDistrict() {
    const district = this.searchDetail.get('district').value;
    this.getAllWardFromDistrict(this.tinh, district);
    this.reList = [];
    this.api.getTradeFromDistrict(district).subscribe(res => {
      this.reList = res;
      // console.log(res);
    });
    this.getDistrictName(this.tinh, district);
  }

  onSubmitSearch() {
    console.log(this.searchDetail.value);
  }

  onKey($event) {
    // console.log(nonAccentVietnamese($event.target.value) );
    this.query = $event.target.value;
  }

  getTradeKind() {
    this.api.getTradeKind().subscribe(tradeKinds => {
      // console.log( tradeKinds);
      this.tradeKinds = tradeKinds;
    });
  }

  getREKind() {
    this.api.getREKind().subscribe(reKinds => {
      // console.log(reKinds);
      this.reKinds = reKinds;
    });
  }

  onChangeREKind() {
    const reKind = this.searchDetail.get('reKind').value;
    this.newREKind = reKind;
    // console.log(reKind);
  }
}
