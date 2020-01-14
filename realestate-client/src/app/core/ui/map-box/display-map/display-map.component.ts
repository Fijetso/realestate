import { ApiService } from 'src/app/services/api/api.service';
import { HttpClient } from '@angular/common/http';
import { Component, AfterViewInit, Input, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MarkerService } from '../../../../services/map/marker.service';
import { LngLat, MapLayerMouseEvent } from 'mapbox-gl';
import { GeoJsonProperties } from 'geojson';
import { DataService } from './../../../../services/data/data.service';
import { GraphQueryService } from './../../../../services/graphql/graph-query.service';

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
  step = 1;
  thumbLabel = true;
  vertical = false;
  prices = [
    {value: 0.5 , label:  '500 triệu '},
    {value: 1 , label:  '1 tỷ '},
    {value: 2 , label:  '2 tỷ '},
    {value: 3 , label:  '3 tỷ '},
    {value: 4 , label:  '4 tỷ '},
    {value: 5 , label:  '5 tỷ '},
    {value: 6 , label:  '6 tỷ '},
    {value: 7 , label:  '7 tỷ '},
    {value: 8 , label:  '8 tỷ '},
    {value: 9 , label:  '9 tỷ '},
    {value: 10 , label:  '10 tỷ '}
  ];

  squares = [
    {value: 20 , label:  'Dưới 30 m2'},
    {value: 35 , label:  '31 m2-40 m2'},
    {value: 45 , label:  '41 m2- 50 m2'},
    {value: 55, label:  '51 m2 -60 m2'},
    {value: 65 , label:  '61 m2-70 m2'},
    {value: 75, label:  '71 m2-80 m2'},
    {value: 85, label:  '81 m2-90 m2'},
    {value: 95, label:  '91 m2- 100 m2'},
    {value: 105, label:  'Trên 100 m2'},
  ];
  priceOption: any = 0 ;
  positionOption: any;
  bedroomValue = 0;
  squareOption: any;
  isLoading;
  tradeKindSlug: any;
  district: any;
  historyData: { userId: any; district: any; price: any; square: any; };
  user: any;
  constructor(private api: ApiService,
              private fb: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private data: DataService,
              private graphql: GraphQueryService
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
    this.tradeKindSlug = this.route.snapshot.paramMap.get('tradeKind').localeCompare('mua') ? 0 : 1;
    this.api.getTradeFromDistrict(this.quan).subscribe(res => {
      this.reList = res;
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
      price: 3,
      squareOption: 0,
      square: 55,
      position: 0,
      bedroom: 0,
      bathroom: 0,
      floors: 0,
    });
    this.isLoading = true;
    this.data.currentUser.subscribe(user => {
      this.user = user;
    });
  }
  ngOnInit(): void {
    this.isFullMap = false;
    this.changeStyle(this.layerId);
    this.isLoading = true;
  }

  changeStyle(layerId: string) {
    this.style = `mapbox://styles/mapbox/${layerId}-v9`;
  }

  ngAfterViewInit(): void {
  }

  mapFullScreen() {
    this.isFullMap = !this.isFullMap;
  }

  getDistrictName(provinceId, districtId) {
    this.api.getDistrictNameById(provinceId, districtId).subscribe(district => {
      this.districtInfo = district;
    });
  }

  getAllDistrict(provinceId) {
    this.isLoading = true;
    this.api.getDistrictFromProvinceId(provinceId).subscribe(districtList => {
      this.districtList = districtList;
      this.isLoading = false;
    });
  }

  getAllWardFromDistrict(provinceId, districtId) {
    this.isLoading = true;
    this.api.getWardFromDistrictId(provinceId, districtId).subscribe(wardList => {
      // console.log(wardList);
      this.wardList = wardList;
      this.isLoading = false;
    });
  }

  onChangeDistrict() {
    this.isLoading = true;
    this.district = this.searchDetail.get('district').value;
    this.getAllWardFromDistrict(this.tinh, this.district);
    this.reList = [];
    this.api.getTradeFromDistrict(this.district).subscribe(res => {
      this.reList = res;
      // console.log(res);
      this.isLoading = false;
    });
    this.getDistrictName(this.tinh, this.district);
  }

  onSubmitSearch() {
    this.historyData = {
      userId: this.user.id,
      district: this.searchDetail.get('district').value,
      price: this.searchDetail.get('price').value,
      square: this.searchDetail.get('square').value
    };
    console.log(this.historyData);
    // this.graphql.saveHistory(
    //   this.historyData.userId,
    //   this.historyData.district,
    //   this.historyData.price,
    //   this.historyData.square)
    // .subscribe(res => {
    //   console.log(res.data.saveHistory);
    // }, error => console.error(error));
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

  selected(evt: MapLayerMouseEvent) {
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

  goToDetail(tradeId) {
    this.router.navigate(['chi-tiet/' + tradeId]);
  }
}
