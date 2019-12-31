import { UserKind } from './../../../../model/user-kind/user-kind';
import { Address } from './../../../../model/address/address';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ApiService } from 'src/app/services/api/api.service';
import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import { environment } from 'src/environments/environment';

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
  startDate: any;
  realImageLink: any;
  constructor(private api: ApiService, private fb: FormBuilder, private router: Router) {
    this.api.getAllUserKind().subscribe(res => {
      // console.info(res);
      this.userKinds = res as UserKind[];
    });

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(pos => {
        this.curLat = pos.coords.latitude;
        this.curLong = pos.coords.longitude;
      });
    }
    this.userInfo = JSON.parse(localStorage.getItem('loginInfo'));
    this.startDate = new Date(1997, 10, 19);
  }

  ngAfterViewInit(): void {
  }
  ngOnInit() {
    this.height = 500;
    this.realEstate = this.fb.group({
      description: 'Mô tả',
      cost: 0,
      user: this.fb.group({
        id: this.userInfo.id,
        name: this.userInfo.name,
        email: this.userInfo.email,
        phone: '0985922740',
        password: '',
        dob: new Date(1997, 10, 19),
        gender: 'true',
        userKind: '1'
      }),
      tradeKind: '1',
      realEstateKind: '3',
      address: this.fb.group({
        id: 0,
        detail: 'detail',
        ward: 0,
        district: 0,
        cityOrProvince: 0
      }),
      details: 'Mô tả chi tiết',
      realImages: 'Mô tả hình ảnh',
      bluePrints: null,
      booking: null
    });
  }

  // Init map


  // Save to create post
  save($event) {
    if (this.selectedFile) {
     const desc = this.realEstate.get('realImages').value;
     this.api.uploadImages(this.selectedFile, desc ).subscribe(res => {
        this.realImageLink = res;
        this.realEstate.get('realImages').setValue(this.realImageLink);
        // tslint:disable-next-line: no-console
        console.info(this.realImageLink);
        // tslint:disable-next-line: no-console
        console.info(this.realEstate.value);
      });
    }
  }

  onChangeGender($event) {
    // tslint:disable-next-line: no-console
    console.info($event.target.value);
  }

  onChangeUserKind($event) {
    // tslint:disable-next-line: no-console
    console.info($event.target.value);
  }
  preview(event) {
    this.selectedFile = event.target.files[0];
    const reader = new FileReader();
    this.imagePath = event.target.files[0];
    reader.readAsDataURL(event.target.files[0]);
    // tslint:disable-next-line: variable-name
    reader.onload = (_event) => {
      this.imgURL = reader.result;
    };
  }

  onSelectFile() {
    const element: HTMLElement = document.querySelector('input[type="file"]') as HTMLElement;
    element.click();
  }
}
