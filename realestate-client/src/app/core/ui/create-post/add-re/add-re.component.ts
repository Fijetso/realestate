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
      description: 'Amet tempor duis eiusmod laboris laboris mollit exercitation dolor ut consequat.',
      cost: 900000000,
      user: this.fb.group({
        id: this.userInfo ? this.userInfo.id : null,
        name: this.userInfo ? this.userInfo.name : null,
        email: this.userInfo ? this.userInfo.email : null,
        phone: '0985922740',
        password: '',
        dob: new Date(1997, 10, 19),
        gender: 'true',
        userKind: '1'
      }),
      tradeKind: '1',
      realEstateKind: '3',
      address: this.fb.group({
        id: 5,
        detail: 'Số nhà 94',
        ward: '27313',
        district: '774',
        cityOrProvince: '79'
      }),
      details: this.fb.group({
        id: 5,
        length: 9,
        width: 4,
        square: 37,
        direction: '2',
        floors: '1 trệt + 1 lầu',
        legalDocuments: 'Sổ hồng riêng',
        bathrooms: 1,
        bedrooms: 2,
        utilities: 'Gần chợ, siêu thị, gần trường học',
        others: 'Bán nhà riêng quận 5, đang cho thuê 6tr/tháng sổ hồng chính chủ'
      }),
      coordinate: this.fb.group({
        id: 5,
        longitude: 106.681,
        latitude: 10.7614
      }),
      realImages: 'Mô tả hình ảnh',
      bluePrints: null,
      booking: null
    });
  }

  // Init map


  // Save to create post
  save($event) {
    // if (this.selectedFile) {
    //   const desc = this.realEstate.get('realImages').value;
    //   this.api.uploadImages(this.selectedFile, desc).subscribe(res => {
    //     this.realImageLink = res;
    //     this.realEstate.get('realImages').setValue(this.realImageLink);
    //     // tslint:disable-next-line: no-console
    //     console.info(this.realImageLink);
    //     // tslint:disable-next-line: no-console
    //     console.info(this.realEstate.value);
    //   });
    // }
    console.info(this.realEstate.value);
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
