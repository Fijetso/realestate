import { UserKind } from './../../../../model/user-kind/user-kind';
import { Address } from './../../../../model/address/address';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { RealEstate } from './../../../../model/real-estate/real-estate';
import { ApiService } from 'src/app/services/api/api.service';
import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import * as L from 'leaflet';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-add-re',
  templateUrl: './add-re.component.html',
  styleUrls: ['./add-re.component.scss']
})
export class AddReComponent implements OnInit, AfterViewInit {
  realEstate: FormGroup;
  public imagePath;
  imgURL: any;
  message: string;
  address: Address;
  userKinds: UserKind[];
  selectedFile: File;
  @Input()
  height: number;
  protected map;
  protected curLat;
  protected curLong;
  markerHtmlStyles2: any;
  userInfo: any;
  constructor(private api: ApiService, private fb: FormBuilder, private router: Router) {
    this.api.getAllUserKind().subscribe(res => {
      // console.info(res);
      this.userKinds = res as UserKind[];
    });

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(pos => {
        this.curLat = pos.coords.latitude;
        this.curLong = pos.coords.longitude;
        this.madeMarkerFromLocation(this.curLat, this.curLong);
      })
    }
    this.userInfo = JSON.parse(localStorage.getItem('loginGoogle'));
    
  }
  ngOnInit() {
    this.height = 200;
    this.realEstate = this.fb.group({
      id: 1,
      description: 'Mô tả',
      cost: 0,
      user: this.fb.group({
        id: this.userInfo.id,
        name: this.userInfo.name,
        email: this.userInfo.email,
        phone: '0985922740',
        password: '',
        birthdate: new Date("1995-19-11"),
        gender: true,
        userKind: this.fb.group({
          id: 1
        })
      }),
      tradeKind: null,
      realEstateKind: null,
      address: this.fb.group({
        id: 0,
        detail: 'detail',
        ward: 0,
        district: 0,
        cityOrProvince: 0
      }),
      details: 'Mô tả chi tiết',
      realImages: null,
      bluePrints: null,
      booking: null
    });
  }

  // Init map 
  ngAfterViewInit(): void {
    this.initMap();
  }


  initMap() {
    const baseLayer = L.tileLayer(
      'https://1.base.maps.api.here.com' +
      '/maptile/2.1/maptile/newest/normal.day/{z}/{x}/{y}/256/png' +
      '?app_id=' + environment.heremap.appId + '&app_code=' + environment.heremap.appCode +
      '&lg=' + environment.heremap.defaultLang
      , {
        maxZoom: 19,
        attribution: '&copy; <a href="http://developer.here.com">HERE</a>',
        updateWhenZooming: true,
      });

    this.map = L.map('map', {
      center:  this.curLat?[this.curLat,this.curLong]:[10.823099, 106.629662],
      zoom: 10,
      drawControl: false,
    });
    baseLayer.addTo(this.map);
  }
  madeMarkerFromLocation(lat,lng) {
    this.markerHtmlStyles2=`
                      background-color: #FD784F;
                      width: 3rem;
                      height: 3rem;
                      display: block;
                      position: relative;
                      border-radius: 3rem 3rem 0;
                      transform: rotate(45deg);
                      text-align: center;
                      padding:1.1rem;
                      color:white;
                      font-size:1.5em;
                      border: 1px solid #FFFFFF`;

    console.info(lat,lng);
    const marker = L.marker([lat,lng],
      {
        icon: L.divIcon({
          className: "my-custom-pin",
          iconAnchor: [0, 24],
          popupAnchor: [0, -36],
          html: `<div style="${this.markerHtmlStyles2}">${1}</div>`
        })
      })
      .bindTooltip(lat+','+lng)
      .bindPopup(lat+','+lng)
      .addTo(this.map);
  }

  // Save to create post
  save($event) {
    // this.api.createRE(this.realEstate.value);
    if (this.selectedFile) {
      this.api.uploadImages(this.selectedFile, 'Mô tả').subscribe(res => {
        // console.info(res);
        return res;
      });
    }
    console.info(this.realEstate.value);
  }

  onChangeGender($event) {
    console.info($event.target.value);
  }

  onChangeUserKind($event) {
    console.info($event.target.value);
  }
  preview(event) {
    this.selectedFile = event.target.files[0]
    let reader = new FileReader();
    this.imagePath = event.target.files[0];
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (_event) => {
      this.imgURL = reader.result;
    }
  }

  onSelectFile() {
    let element: HTMLElement = document.querySelector('input[type="file"]') as HTMLElement;
    element.click();
  }
}
