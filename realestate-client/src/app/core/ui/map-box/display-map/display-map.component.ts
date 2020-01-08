import { ApiService } from 'src/app/services/api/api.service';
import { HttpClient } from '@angular/common/http';
import { Component, AfterViewInit, Input, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MarkerService } from '../../../../services/map/marker.service';
import { LngLat, MapMouseEvent, MapLayerMouseEvent } from 'mapbox-gl';
import { GeoJsonProperties } from 'geojson';

@Component({
  selector: 'app-display-map',
  templateUrl: './display-map.component.html',
  styleUrls: ['./display-map.component.scss']
})
export class DisplayMapComponent implements AfterViewInit, OnInit {
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
  cursorStyle: string;
  selectedElement: GeoJsonProperties;
  selectedLngLat: LngLat;
  selectedMarker: LngLat;
  // slider
  autoTicks = false;
  disabled = false;
  invert = false;
  max = 10;
  min = 0;
  showTicks = true;
  step = 0.5;
  thumbLabel = true;
  vertical = false;
  prices = [
    {value: 0 , label:  '0 tỷ '},
    {value: 0.5 , label:  '0.5 tỷ '},
    {value: 1 , label:  '1 tỷ '},
    {value: 1.5 , label:  '1.5 tỷ '},
    {value: 2 , label:  '3 tỷ '},
    {value: 2.5 , label:  '2.5 tỷ '},
    {value: 3 , label:  '3 tỷ '},
    {value: 3.5 , label:  '3.5 tỷ '},
    {value: 4 , label:  '4 tỷ '},
    {value: 4.5 , label:  '4.5 tỷ '},
    {value: 5 , label:  '5 tỷ '},
    {value: 5.5 , label:  '5.5 tỷ '},
    {value: 6 , label:  '6 tỷ '},
    {value:  6.5, label:  '6.5 tỷ'},
    {value: 7 , label:  '7 tỷ '},
    {value: 7.5 , label:  '7.5 tỷ '},
    {value: 8 , label:  '8 tỷ '},
    {value: 8.5 , label:  '8.5 tỷ '},
    {value: 9 , label:  '9 tỷ '},
    {value: 9.5 , label:  '9.5 tỷ '},
    {value: 10 , label:  '10 tỷ '}
  ];

  squares = [
    {value: 100 , label:  '100 m2'},
    {value: 200 , label:  '200 m2'},
    {value: 300 , label:  '300 m2'},
    {value: 400, label:  '400 m2'},
    {value: 500 , label:  '500 m2'},
    {value: 600, label:  '600 m2'},
    {value: 700, label:  '700 m2'},
    {value: 800, label:  '800 m2'},
    {value: 900, label:  '900 m2'},
    {value: 1000, label:  '1000 m2'},
  ];
  priceOption: any = 0 ;
  positionOption: any;
  bedroomValue = 0;
  squareOption: any;
  constructor(private markerService: MarkerService,
              private http: HttpClient,
              private api: ApiService,
              private fb: FormBuilder,
              private route: ActivatedRoute,
              private changeDetectorRef: ChangeDetectorRef
              ) {
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
      ward: '',
      priceOption: 0,
      price: 0,
      squareOption: 0,
      square: 100,
      position: 0,
      bedroom: 0,
      bathroom: 0,
      floors: 0,
    });
  }
  ngOnInit(): void {
    this.isFullMap = false;
    this.changeStyle(this.layerId);
    console.log(this.priceOption);
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

  onClick(evt: MapLayerMouseEvent) {
    this.selectedLngLat = evt.lngLat;
    this.selectedElement = evt.features[0].properties;
  }

  handleChange(name) {
    if (name === 'priceOption') {
      this.priceOption = this.searchDetail.get(name).value;
    }
    if (name === 'squareOption') {
      this.squareOption = this.searchDetail.get(name).value;
    }
  }
}
